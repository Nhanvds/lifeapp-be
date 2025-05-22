package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.dto.request.IngredientReq;
import com.mad.lifeapp.dto.request.FoodRequest;
import com.mad.lifeapp.dto.response.FoodCategory;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.dto.response.IngredientRes;
import com.mad.lifeapp.entity.*;
import com.mad.lifeapp.enums.CategoryEnum;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.mapper.FoodMapper;
import com.mad.lifeapp.mapper.IngredientMapper;
import com.mad.lifeapp.repository.*;
import com.mad.lifeapp.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLClientInfoException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.rmi.server.LogStream.log;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class FoodServiceImpl implements FoodService {
    private final UserRepository userRepository;

    private final FoodReponsitory foodReponsitory;
    private final FoodMapper foodMapper;
    private final IngredientMapper ingredientMapper;
    private final IngredientReponsitory ingredientReponsitory;
    private final FoodIngredientReponsitory foodIngredientReponsitory;
    private final NoteFoodRepository noteFoodRepository;

    public Long getCurrentUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }

        Jwt jwt = (Jwt) auth.getPrincipal();



        return (Long) jwt.getClaim("user_id");
    }

    @Override
    public FoodResponse getFood(Long id) throws NotFoundException {
        Long idUser = getCurrentUserId();
        UserEntity userEntity = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Khong tim thay user"));
        FoodEntity foodEntity = foodReponsitory.findById(idUser, id).orElseThrow(() -> new RuntimeException("Khong tim thay food"));

        FoodResponse foodResponse = foodMapper.toFoodResponse(foodEntity);
        NoteFoodEntity noteFoodEntity = noteFoodRepository.findByUserAndFood(userEntity, foodEntity);
        Set<IngredientRes> ingredientRes = new HashSet<>();
        for(FoodIngredientEntity foodIngredientEntity : foodEntity.getFoodIngredients()){
            ingredientRes.add(IngredientRes.builder()
                            .name(foodIngredientEntity.getIngredient().getName())
                            .gram(foodIngredientEntity.getGram())
                            .imageUrl(foodIngredientEntity.getIngredient().getImageUrl())
                    .build());
        }

        foodResponse.setNote(Objects.isNull(noteFoodEntity) ? "": noteFoodEntity.getNote());

        foodResponse.setIngredients(ingredientRes);
        return foodResponse;

    }


    @Override
    public List<FoodResponse> getFoods(){
        Long idUser = getCurrentUserId();

        List<FoodEntity> foodEntities = foodReponsitory.findByCreatedBy(idUser);
        List<FoodResponse> foodResponses = new ArrayList<>();

        foodEntities.forEach(foodEntity -> {
            foodResponses.add(foodMapper.toFoodResponse(foodEntity));
        });

        return foodResponses;
    }

    @Override
    public Boolean addFood(FoodRequest foodRequest) throws InvalidException {
        Long idUser = getCurrentUserId();

        try {
            FoodEntity food = foodMapper.toFood(foodRequest);
            food.setCreatedBy(idUser);
            Set<IngredientReq> ingredientReqSet = foodRequest.getIngredients();


//            food.setCreatedBy(foodRequest.getCreatedBy());
//            log.info(foodRequest.getCreatedBy().toString() + " " + food.getCreatedBy());
            FoodEntity foodEntity = foodReponsitory.save(food);

            for (IngredientReq item : ingredientReqSet) {
                IngredientsEntity ingredientsEntity = ingredientMapper.toIngredient(item);
                IngredientsEntity ingredientsEntitySave = ingredientReponsitory.save(ingredientsEntity);
                foodIngredientReponsitory.save(FoodIngredientEntity.builder()
                        .food(foodEntity)
                        .gram(item.getGram())
                        .ingredient(ingredientsEntitySave)
                        .build());
            }


        } catch (Exception e) {
            throw new InvalidException(e.getMessage());
        }

        return true;
    }

    @Override
    public Boolean updateFood(Long id, String note) {
        Long idUser = getCurrentUserId();


        UserEntity userEntity = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException(("Khong tim thay id cua User")));
        FoodEntity food = foodReponsitory.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay id cua food"));

        NoteFoodEntity noteFoodEntityCheck = noteFoodRepository.findByUserAndFood(userEntity, food);

        try {
            if (noteFoodEntityCheck == null) {
                NoteFoodEntity noteFoodEntity1 = NoteFoodEntity.builder()
                        .note(note)
                        .food(food)
                        .user(userEntity)
                        .build();
                noteFoodRepository.save(noteFoodEntity1);
            } else {
                noteFoodEntityCheck.setNote(note);
                noteFoodRepository.save(noteFoodEntityCheck);
            }
        }catch (Exception e){
            log(e.getMessage());
        }

        return true;
    }

    @Override
    public Boolean deleteFood(Long id) {
        return null;
    }

    @Override
    public FoodCategory getFoodCategorys(CategoryEnum category, Pageable pageable) {
        Long idUser = getCurrentUserId();

        System.out.println(idUser);

        Page<FoodEntity> foodEntityPage;

        if(category == CategoryEnum.All) {
            foodEntityPage = foodReponsitory.findAllFood("", idUser, pageable);
        }
        else {
            foodEntityPage = foodReponsitory.findByCategoryAndCreatedByOrCreatedBy(category.toString(), idUser,  pageable);
        }
        Page<FoodResponse> foodResponsePage = foodEntityPage.map(foodMapper::toFoodResponse);
        return FoodCategory.builder()
                .foodResponses(foodResponsePage.getContent().stream().collect(Collectors.toSet()))
                .pageNumber(foodResponsePage.getPageable().getPageNumber())
                .totalPages(foodResponsePage.getTotalPages())
                .build();


    }

    @Override
    public FoodCategory findFood(String nameFood, String category,Integer checkOptionTime, Integer time, Integer typeCalo, Pageable pageable) {

        Long idUser = getCurrentUserId();

        Page<FoodEntity> foodEntityPage;

        category = category.substring(0,1).toUpperCase() + category.substring(1).toLowerCase();

        if(category.equals("All")) category = "" ;
        Integer startCalo = 0;
        Integer endCalo = Integer.MAX_VALUE;

        switch (typeCalo){
            case 1: {
                startCalo = 0;
                endCalo = 500;
                break;
            }
            case 2: {
                startCalo = 500;
                endCalo = 1000;
                break;
            }
            case 3: {
                startCalo = 1000;
                endCalo = 2000;
                break;
            }
            case 4: {
                startCalo = 2000;
                endCalo = 10000;
                break;
            }

        }


        if(checkOptionTime == 1) foodEntityPage = foodReponsitory.findNameTime(nameFood, time,category, startCalo, endCalo,  idUser, pageable);
        else foodEntityPage = foodReponsitory.findNameNoTime(nameFood, category, startCalo, endCalo, idUser, pageable);

        Page<FoodResponse> foodResponsePage = foodEntityPage.map(foodMapper::toFoodResponse);
        FoodCategory foodCategory = FoodCategory.builder()
                .foodResponses(foodResponsePage.getContent().stream().collect(Collectors.toSet()))
                .pageNumber(foodResponsePage.getPageable().getPageNumber())
                .totalPages(foodResponsePage.getTotalPages())
                .build();

        return foodCategory;
    }

    @Override
    public Boolean addFoods(List<FoodRequest> foodRequests) throws InvalidException {
        Long idUser = 0L;

        foodRequests.forEach(foodRequest -> {
            try {
                FoodEntity food = foodMapper.toFood(foodRequest);
                food.setCreatedBy(idUser);
                Set<IngredientReq> ingredientReqSet = foodRequest.getIngredients();


//            food.setCreatedBy(foodRequest.getCreatedBy());
//            log.info(foodRequest.getCreatedBy().toString() + " " + food.getCreatedBy());
                FoodEntity foodEntity = foodReponsitory.save(food);

                for (IngredientReq item : ingredientReqSet) {
                    IngredientsEntity ingredientsEntity = ingredientMapper.toIngredient(item);
                    IngredientsEntity ingredientsEntitySave = ingredientReponsitory.save(ingredientsEntity);
                    foodIngredientReponsitory.save(FoodIngredientEntity.builder()
                            .food(foodEntity)
                            .gram(item.getGram())
                            .ingredient(ingredientsEntitySave)
                            .build());
                }


            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        });

        return true;

    }


    @Override
    public List<FoodResponse> getSuggests(String category, Float targetCalo) {
        // Chuyển category từ String sang CategoryEnum
//        try {
//            categoryEnum = CategoryEnum.valueOf(category);
//        } catch (IllegalArgumentException e) {
//            return Collections.emptyList(); // Trả về rỗng nếu category không hợp lệ
//        }

        // Lấy danh sách món ăn theo category
        List<FoodEntity> foods = foodReponsitory.findByCategory(category);
        if (foods.isEmpty()) {
            return Collections.emptyList();
        }

        // Chuyển FoodEntity sang FoodResponse
        List<FoodResponse> foodResponses = foods.stream().map(foodMapper::toFoodResponse).collect(Collectors.toList());
//                .map(this::convertToFoodResponse)
//                .collect(Collectors.toList());

        // Tìm tổ hợp món ăn có tổng calo gần targetCalo nhất
        return findOptimalCombination(foodResponses, targetCalo);
    }

    // Tính calo của một món ăn
    private float calculateCalories(FoodResponse food) {
        return (food.getProtein() * 4) + (food.getFat() * 9) + (food.getCarbs() * 4);
    }

    // Tìm tổ hợp món ăn tối ưu
    private List<FoodResponse> findOptimalCombination(List<FoodResponse> foods, float targetCalo) {
        // Lưu trữ các tổ hợp tốt nhất
        List<Pair<List<FoodResponse>, Float>> candidates = new ArrayList<>();
        int n = foods.size();

        // Duyệt tất cả tổ hợp (tối đa 3 món)
        for (int i = 1; i < (1 << n); i++) {
            List<FoodResponse> combo = new ArrayList<>();
            float totalCalo = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    combo.add(foods.get(j));
                    totalCalo += calculateCalories(foods.get(j));
                }
            }
            // Chỉ chọn tổ hợp có tối đa 3 món
            if (combo.size() <= 3) {
                candidates.add(Pair.of(new ArrayList<>(combo), Math.abs(totalCalo - targetCalo)));
            }
        }

        // Sắp xếp theo độ chênh lệch calo tăng dần
        candidates.sort(Comparator.comparing(Pair::getSecond));

//        // Lấy tối đa 3 tổ hợp tốt nhất
//        List<FoodResponse> result = new ArrayList<>();
//        for (int i = 0; i < Math.min(3, candidates.size()); i++) {
//            result.addAll(candidates.get(i).getFirst());
//        }
        return candidates.get(LocalDate.now().getDayOfMonth() % 3).getFirst();
    }
}
