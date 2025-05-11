package com.mad.lifeapp.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DailyRes {
    Long id;
    String note;
    Set<DailyMenuFoodRes> dailyMenuFoodDTO;
}
