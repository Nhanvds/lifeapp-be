package com.mad.lifeapp.dto.response;

import lombok.Data;

@Data
public class CaloDataDTO {
    private Double breakfastCalo;
    private Double lunchCalo;
    private Double dinnerCalo;
    private Float totalCalo;

    public CaloDataDTO(Double breakfastCalo, Double lunchCalo, Double dinnerCalo, Float totalCalo) {
        this.breakfastCalo = breakfastCalo != null ? breakfastCalo : 0.0;
        this.lunchCalo = lunchCalo != null ? lunchCalo : 0.0;
        this.dinnerCalo = dinnerCalo != null ? dinnerCalo : 0.0;
        this.totalCalo = totalCalo != null ? totalCalo : 0.0f;
    }
}