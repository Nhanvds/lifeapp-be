package com.mad.lifeapp.dto.request;

import com.mad.lifeapp.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {
//    private Long id;
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
    private Set<IngredientReq> ingredients;
}
