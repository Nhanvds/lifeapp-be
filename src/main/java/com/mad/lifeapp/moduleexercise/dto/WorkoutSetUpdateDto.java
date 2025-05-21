package com.mad.lifeapp.moduleexercise.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkoutSetUpdateDto {
    private Integer currentExerciseIndex;
    private LocalDateTime endTime;

    // getters + setters
}
