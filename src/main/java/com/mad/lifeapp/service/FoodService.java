package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.FoodRequest;
import com.mad.lifeapp.dto.response.FoodCategory;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.enums.CategoryEnum;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {
    FoodResponse getFood (Long foodId) throws NotFoundException;

    List<FoodResponse> getFoods();
    FoodCategory getFoodCategorys (CategoryEnum category, Pageable pageable);

    Boolean updateFood(FoodRequest foodRequest, Long id);
    Boolean deleteFood(Long id);
    Boolean addFood(FoodRequest foodRequest) throws InvalidException;
    FoodCategory findFood(String nameFood, String nameCategory, Integer checkOptionTime, Integer time, Integer typeCalo, Pageable pageable);

}
