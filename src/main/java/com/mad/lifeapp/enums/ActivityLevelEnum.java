package com.mad.lifeapp.enums;

public enum ActivityLevelEnum {

    SEDENTARY(1.2),        // Ít vận động
    LIGHTLY_ACTIVE(1.375), // Hoạt động nhẹ
    MODERATELY_ACTIVE(1.55), // Hoạt động vừa
    VERY_ACTIVE(1.725),    // Hoạt động nhiều
    EXTRA_ACTIVE(1.9);     // Hoạt động rất nhiều

    private final double activityFactor;

    ActivityLevelEnum(double activityFactor) {
        this.activityFactor = activityFactor;
    }

    public double getActivityFactor() {
        return activityFactor;
    }
}
