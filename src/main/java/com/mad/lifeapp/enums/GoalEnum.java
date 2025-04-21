package com.mad.lifeapp.enums;

public enum GoalEnum {
    LOSE_WEIGHT(-500),   // Giảm 500 kcal
    GAIN_WEIGHT(500),    // Tăng 500 kcal
    MAINTAIN_WEIGHT(0),  // Không thay đổi
    BUILD_MUSCLE(300);   // Tăng 300 kcal

    private final int calorieAdjustment;

    GoalEnum(int calorieAdjustment) {
        this.calorieAdjustment = calorieAdjustment;
    }

    public int getCalorieAdjustment() {
        return calorieAdjustment;
    }
}
