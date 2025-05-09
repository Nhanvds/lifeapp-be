package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.dto.request.FoodRequest;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.entity.FoodEntity;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.mapper.FoodMapper;
import com.mad.lifeapp.repository.FoodReponsitory;
import com.mad.lifeapp.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class FoodServiceImpl implements FoodService {

    private final FoodReponsitory foodReponsitory;
    private final FoodMapper foodMapper;
    @Override
    public FoodResponse getFood(Long id) throws NotFoundException {
        Optional<FoodEntity> foodEntity = foodReponsitory.findById(id);
        if(foodEntity.isEmpty()) {
            throw new NotFoundException("Không tìm thấy Food");
        }

        return foodMapper.toFoodResponse(foodEntity.get());

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

    @Override
    public Boolean addFood(FoodRequest foodRequest) throws InvalidException {

        try {
            FoodEntity food = foodMapper.toFood(foodRequest);
//            food.setCreatedBy(foodRequest.getCreatedBy());
            log.info(foodRequest.getCreatedBy().toString() + " " + food.getCreatedBy());
            FoodEntity foodEntity = foodReponsitory.save(food);
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
}
