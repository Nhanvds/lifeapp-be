package com.mad.lifeapp.dto.request;

import com.mad.lifeapp.dto.response.FoodResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyMenuFoodReq {
    private String category;
    private FoodResponse food;
}
