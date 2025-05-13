package com.mad.lifeapp.dto;

import lombok.Data;

@Data
public class MacrosDataDTO {
    private Double protein;
    private Double carbohydrates;
    private Double fats;
    private Long totalCalo;

    public MacrosDataDTO(Double protein, Double carbohydrates, Double fats, Long totalCalo) {
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.totalCalo = totalCalo;
    }

    public MacrosDataDTO() {
    }
}