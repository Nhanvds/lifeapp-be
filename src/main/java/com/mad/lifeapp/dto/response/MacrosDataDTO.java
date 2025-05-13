package com.mad.lifeapp.dto.response;

import lombok.Data;

@Data
public class MacrosDataDTO {
    private Float protein;
    private Float carbohydrates;
    private Float fats;
    private Float totalCalo;

    public MacrosDataDTO(Double protein, Double carbohydrates, Double fats, Float totalCalo) {
        this.protein = protein != null ? protein.floatValue() : 0.0f;
        this.carbohydrates = carbohydrates != null ? carbohydrates.floatValue() : 0.0f;
        this.fats = fats != null ? fats.floatValue() : 0.0f;
        this.totalCalo = totalCalo != null ? totalCalo : 0.0f;
    }
}