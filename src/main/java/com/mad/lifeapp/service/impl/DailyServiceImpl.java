package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.component.JwtUtils;
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
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
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
            dailyMenuEntity = dailyReponsitory.save(DailyMenuEntity.builder()
                            .user(userRepository.findById(idUser).get())
                            .day(localDate)
                    .build());

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
    public Boolean updateDaily(List<Long> idFoods, String note, Long id) {
        try {
            idFoods.forEach(item -> dailyMenuFoodReponsitory.deleteById(item));
            DailyMenuEntity dailyMenuEntity = dailyReponsitory.findById(id).get();
            dailyMenuEntity.setNote(note);
            dailyReponsitory.save(dailyMenuEntity);
        }catch (Exception e){
            throw new RuntimeException("Update DailyMenuFood khong thanh cong");
        }
        return true;
    }
}