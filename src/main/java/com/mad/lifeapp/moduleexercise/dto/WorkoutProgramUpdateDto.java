package com.mad.lifeapp.moduleexercise.dto;

import lombok.Data;

@Data
public class WorkoutProgramUpdateDto {
    private String name;
    private Integer categoryId;
    private String imageUrl;
}
