package com.mad.lifeapp.dto;

import lombok.Data;

@Data
public class CaloDataDTO {
    private Double breakfastCalo;
    private Double lunchCalo;
    private Double dinnerCalo;
    private Long totalCalo;

    public CaloDataDTO(Double breakfastCalo, Double lunchCalo, Double dinnerCalo, Long totalCalo) {
        this.breakfastCalo = breakfastCalo;
        this.lunchCalo = lunchCalo;
        this.dinnerCalo = dinnerCalo;
        this.totalCalo = totalCalo;
    }

    public CaloDataDTO() {
    }
}