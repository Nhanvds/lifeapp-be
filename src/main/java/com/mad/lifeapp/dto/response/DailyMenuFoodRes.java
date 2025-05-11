package com.mad.lifeapp.dto.response;

import com.mad.lifeapp.entity.DailyMenuFoodEntity;
import com.mad.lifeapp.entity.FoodEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DailyMenuFoodRes {
    private Long id;
    private String category;
    private FoodResponse food;
}
