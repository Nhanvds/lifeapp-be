package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.FoodRequest;
import com.mad.lifeapp.dto.response.FoodResponse;
import com.mad.lifeapp.enums.CategoryEnum;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {
    FoodResponse getFood (Long foodId) throws NotFoundException;

    List<FoodResponse> getFoods();
    Page<FoodResponse> getFoodCategorys (CategoryEnum category, Pageable pageable);

    Boolean updateFood(FoodRequest foodRequest, Long id);
    Boolean deleteFood(Long id);

    Boolean addFood(FoodRequest foodRequest) throws InvalidException;

}
