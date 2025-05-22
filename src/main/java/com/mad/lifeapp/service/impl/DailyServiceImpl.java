package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.component.JwtUtils;
import com.mad.lifeapp.dto.request.DailyReq;
import com.mad.lifeapp.dto.response.DailyMenuFoodRes;
import com.mad.lifeapp.dto.response.DailyRes;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.dto.response.IngredientRes;
import com.mad.lifeapp.entity.*;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.mapper.FoodMapper;
import com.mad.lifeapp.mapper.IngredientMapper;
import com.mad.lifeapp.repository.DailyMenuFoodReponsitory;
import com.mad.lifeapp.repository.DailyReponsitory;
import com.mad.lifeapp.repository.FoodReponsitory;
import com.mad.lifeapp.repository.UserRepository;
import com.mad.lifeapp.service.DailyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static java.rmi.server.LogStream.log;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DailyServiceImpl implements DailyService {

    public final JwtUtils jwtUtils;
    public final DailyReponsitory dailyReponsitory;
    public final UserRepository userRepository;
    public final FoodReponsitory foodReponsitory;
    public final DailyMenuFoodReponsitory dailyMenuFoodReponsitory;
    public final FoodMapper foodMapper;
    public final IngredientMapper ingredientMapper;
    @Override
    public DailyRes getDailyMenuFoodRes(LocalDate localDate, String token) {
        Long idUser = 0L;
        UserEntity user = new UserEntity();
        DailyMenuEntity dailyMenuEntity = new DailyMenuEntity();
        try {
            idUser = jwtUtils.getUserId(token);
            user = userRepository.findById(idUser).get();

        }
        catch (Exception e){
            throw new RuntimeException("Loi lay id or getUser or getDaily khong dung trong getDailyMenuFoodRes");
        }
        DailyRes dailyRes = DailyRes.builder().build();
        dailyMenuEntity = dailyReponsitory.findByUserAndDay(user, localDate);

        if(Objects.isNull(dailyMenuEntity)){
            try {
                dailyMenuEntity = dailyReponsitory.save(DailyMenuEntity.builder()
                        .user(userRepository.findById(idUser).get())
                        .day(localDate)
                        .build());
            }
            catch (Exception e){
                log(e.getMessage());
            }

            dailyRes = DailyRes.builder()
                    .id(dailyMenuEntity.getId())
                    .build();

            return dailyRes;
        }

        Set<DailyMenuFoodRes> dailyMenuFoodResSet = new HashSet<>();
        for(DailyMenuFoodEntity dailyMenuFoodRes : dailyMenuEntity.getDailyMenuFoods()){
            FoodEntity food = dailyMenuFoodRes.getFood();
            FoodResponse foodResponse = foodMapper.toFoodResponse(food);
            Set<IngredientRes> ingredientResSet = new HashSet<>();

            for(FoodIngredientEntity foodIngredientEntity : food.getFoodIngredients()){
                   IngredientRes ingredientRes = ingredientMapper.toIngredientRes(foodIngredientEntity.getIngredient());
                   ingredientRes.setGram(foodIngredientEntity.getGram());
            }
            foodResponse.setIngredients(ingredientResSet);
            dailyMenuFoodResSet.add(DailyMenuFoodRes.builder()
                        .id(dailyMenuFoodRes.getId())
                      .food(foodResponse)
                      .category(dailyMenuFoodRes.getCategory())
                      .build());
        }
        dailyRes = DailyRes.builder()
                .id(dailyMenuEntity.getId())
                .note(dailyMenuEntity.getNote())
                .dailyMenuFoodDTO(dailyMenuFoodResSet)
                .build();

        return dailyRes;
    }

    @Override
    public Boolean addDailyMenuFood(Long dailyId, Long foodId, String nameCategory) {
        try {
            DailyMenuEntity daily = dailyReponsitory.findById(dailyId).get();
            FoodEntity food = foodReponsitory.findById(foodId).get();

            dailyMenuFoodReponsitory.save(DailyMenuFoodEntity.builder()
                            .dailyMenuEntity(daily)
                            .category(nameCategory)
                            .food(food)
                    .build());
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return true;
    }

    @Override
    public Boolean updateDaily(List<Long> idDeleteFoods, String note, Long id) {
        try {
            idDeleteFoods.forEach(item -> {
                DailyMenuFoodEntity dailyMenuFoodEntity = dailyMenuFoodReponsitory.findById(item).orElseThrow(() ->new RuntimeException("Khong tim thay food"));
                dailyMenuFoodReponsitory.deleteByIdDailyMenuFood(dailyMenuFoodEntity.getId());
                System.out.println(dailyMenuFoodReponsitory.findById(item));

            });

            DailyMenuEntity dailyMenuEntity = dailyReponsitory.findById(id).get();
            dailyMenuEntity.setNote(note);
            dailyReponsitory.save(dailyMenuEntity);
        }catch (Exception e){
            throw new RuntimeException("Update DailyMenuFood khong thanh cong: " + e.getMessage());
        }
        return true;
    }

    @Override
    public DailyRes applyDailySuggest(DailyReq dailyReq) {
        Long id = dailyReq.getId();



        DailyMenuEntity dailyMenuEntity = dailyReponsitory.findById(id).orElseThrow(() -> new RuntimeException("Khong tim duoc daily"));
        dailyMenuFoodReponsitory.deleteByDailyMenuEntity(dailyMenuEntity);
        dailyMenuEntity.setNote(dailyReq.getNote());
        Set<DailyMenuFoodEntity> dailyMenuFoodEntities = new HashSet<>();

        if(!dailyReq.getDailyMenuFoodDTO().isEmpty()) {
            dailyReq.getDailyMenuFoodDTO().forEach(item -> {
                FoodEntity food = foodReponsitory.findById(item.getFood().getId()).orElseThrow(() -> new RuntimeException("Khong tim duoc food"));
                DailyMenuFoodEntity dailyMenuFoodEntity = dailyMenuFoodReponsitory.save(DailyMenuFoodEntity.builder()
                        .food(food)
                        .category(item.getCategory())
                        .dailyMenuEntity(dailyMenuEntity)
                        .build());

                dailyMenuFoodEntities.add(dailyMenuFoodEntity);
            });
        }

        dailyMenuEntity.setDailyMenuFoods(dailyMenuFoodEntities);

        dailyReponsitory.save(dailyMenuEntity);

        Set<DailyMenuFoodRes> dailyMenuFoodResSet = new HashSet<>();
        for(DailyMenuFoodEntity dailyMenuFoodRes : dailyMenuEntity.getDailyMenuFoods()){
            FoodEntity food = dailyMenuFoodRes.getFood();
            FoodResponse foodResponse = foodMapper.toFoodResponse(food);
            Set<IngredientRes> ingredientResSet = new HashSet<>();

            for(FoodIngredientEntity foodIngredientEntity : food.getFoodIngredients()){
                IngredientRes ingredientRes = ingredientMapper.toIngredientRes(foodIngredientEntity.getIngredient());
                ingredientRes.setGram(foodIngredientEntity.getGram());
            }
            foodResponse.setIngredients(ingredientResSet);
            dailyMenuFoodResSet.add(DailyMenuFoodRes.builder()
                    .id(dailyMenuFoodRes.getId())
                    .food(foodResponse)
                    .category(dailyMenuFoodRes.getCategory())
                    .build());
        }

        DailyRes dailyRes = DailyRes.builder()
                .note(dailyMenuEntity.getNote())
                .dailyMenuFoodDTO(dailyMenuFoodResSet)
                .id(id)
                .build();
        return dailyRes;



    }
}