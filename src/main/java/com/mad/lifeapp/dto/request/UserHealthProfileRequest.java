package com.mad.lifeapp.dto.request;

import com.mad.lifeapp.enums.ActivityLevelEnum;
import com.mad.lifeapp.enums.GoalEnum;
import com.mad.lifeapp.enums.UserGenderEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserHealthProfileRequest {

    private UserGenderEnum gender;
    private Integer age;
    private Float height;
    private Float weight;
    private GoalEnum goal;
    private ActivityLevelEnum activityLevel;
}
