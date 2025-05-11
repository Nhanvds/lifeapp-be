package com.mad.lifeapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DailyCalorieStatsDTO {
    private LocalDate date;
    private Double totalCalories;

    public DailyCalorieStatsDTO(LocalDate date, Double totalCalories) {
        this.date = date;
        this.totalCalories = totalCalories;
    }

    // Getters and optionally setters
}
