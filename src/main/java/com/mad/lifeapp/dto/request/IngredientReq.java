package com.mad.lifeapp.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientReq {
//    private Long id;
    private String name;
    private String imageUrl;
    private Float gram;
}
