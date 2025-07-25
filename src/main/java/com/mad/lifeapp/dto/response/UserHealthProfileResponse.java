package com.mad.lifeapp.dto.response;

import com.mad.lifeapp.enums.ActivityLevelEnum;
import com.mad.lifeapp.enums.GoalEnum;
import com.mad.lifeapp.enums.UserGenderEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserHealthProfileResponse {
    private UserGenderEnum gender;
    private Integer age;
    private Float height;
    private Float weight;
    private GoalEnum goal;
    private ActivityLevelEnum activityLevel;
    private Float dailyCaloriesGoal;
    private LocalDateTime createdAt;
    private LocalDate queryDate;
}
