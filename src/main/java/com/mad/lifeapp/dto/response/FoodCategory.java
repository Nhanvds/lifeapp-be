package com.mad.lifeapp.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class FoodCategory {
    Integer pageNumber;
    Integer totalPages;
    Set<FoodResponse> foodResponses;
}
