package com.mad.lifeapp.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NutritionRequestDTO {
    @NotNull(message = "Day is required")
    private LocalDate day;

    @NotNull(message = "User ID is required")
    private Long userId;
}