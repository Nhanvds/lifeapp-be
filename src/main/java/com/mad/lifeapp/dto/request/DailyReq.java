package com.mad.lifeapp.dto.request;

import com.mad.lifeapp.dto.response.DailyMenuFoodRes;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DailyReq {
    Long id;
    String note;
    Set<DailyMenuFoodReq> dailyMenuFoodDTO;
}
