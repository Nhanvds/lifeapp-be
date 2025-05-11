package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.dto.request.IngredientReq;
import com.mad.lifeapp.dto.request.FoodRequest;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.dto.response.IngredientRes;
import com.mad.lifeapp.entity.FoodEntity;
import com.mad.lifeapp.entity.FoodIngredientEntity;
import com.mad.lifeapp.entity.IngredientsEntity;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.mapper.FoodMapper;
import com.mad.lifeapp.mapper.IngredientMapper;
import com.mad.lifeapp.repository.FoodIngredientReponsitory;
import com.mad.lifeapp.repository.FoodReponsitory;
import com.mad.lifeapp.repository.IngredientReponsitory;
import com.mad.lifeapp.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class FoodServiceImpl implements FoodService {

    private final FoodReponsitory foodReponsitory;
    private final FoodMapper foodMapper;
    private final IngredientMapper ingredientMapper;
    private final IngredientReponsitory ingredientReponsitory;
    private final FoodIngredientReponsitory foodIngredientReponsitory;
    @Override
    public FoodResponse getFood(Long id) throws NotFoundException {
        Optional<FoodEntity> foodEntity = foodReponsitory.findById(id);
        if(foodEntity.isEmpty()) {
            throw new NotFoundException("Không tìm thấy Food");
        }


        FoodResponse foodResponse = foodMapper.toFoodResponse(foodEntity.get());
        Set<IngredientRes> ingredientRes = new HashSet<>();
        for(FoodIngredientEntity foodIngredientEntity : foodEntity.get().getFoodIngredients()){
            ingredientRes.add(IngredientRes.builder()
                            .name(foodIngredientEntity.getIngredient().getName())
                            .gram(foodIngredientEntity.getGram())
                            .imageUrl(foodIngredientEntity.getIngredient().getImageUrl())
                    .build());
        }

        foodResponse.setIngredients(ingredientRes);
        return foodResponse;

    }


    @Override
    public List<FoodResponse> getFoods(){

        List<FoodEntity> foodEntities = foodReponsitory.findAll();
        List<FoodResponse> foodResponses = new ArrayList<>();

        foodEntities.forEach(foodEntity -> {
            foodResponses.add(foodMapper.toFoodResponse(foodEntity));
        });

        return foodResponses;
    }

    @Transactional
    @Override
    public Boolean addFood(FoodRequest foodRequest) throws InvalidException {

        try {
            FoodEntity food = foodMapper.toFood(foodRequest);
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
    public Boolean updateFood(FoodRequest foodRequest, Long id) {
        return null;
    }

    @Override
    public Boolean deleteFood(Long id) {
        return null;
    }

    @Override
    public Page<FoodResponse> getFoodCategorys(String category, Pageable pageable) {

        Page<FoodEntity> foodEntityPage = foodReponsitory.findByCategory(category, pageable);
        Page<FoodResponse> foodResponses = foodEntityPage.map(foodMapper :: toFoodResponse);

        return foodResponses;
    }
}
