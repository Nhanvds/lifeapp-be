package com.mad.lifeapp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientRes {
    private String name;
    private String imageUrl;
    private Float gram;
}
