package com.mad.lifeapp.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StatisticsResponse {
    private LocalDate date;
    private Float breakfastCalo;
    private Float lunchCalo;
    private Float dinnerCalo;
    private Float totalCalories;
    private Float weight;
}