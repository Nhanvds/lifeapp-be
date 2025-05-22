package com.mad.lifeapp.dto.response;

import com.mad.lifeapp.dto.request.IngredientReq;
import com.mad.lifeapp.entity.NoteFoodEntity;
import com.mad.lifeapp.enums.CategoryEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class FoodResponse {
    private Long id;
    private String name;
    private String description;
    private Integer cookingTime;
    private String imageUrl;
    private String level;
    private Float protein;
    private Float fat;
    private Float carbs;
    private CategoryEnum category;
    private Long createdBy;
    private String note;
    private Set<IngredientRes> ingredients;
}
