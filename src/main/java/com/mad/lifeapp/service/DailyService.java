package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.DailyReq;
import com.mad.lifeapp.dto.response.DailyRes;

import java.time.LocalDate;
import java.util.List;

public interface DailyService {
    DailyRes getDailyMenuFoodRes(LocalDate localDate, String token);

    Boolean addDailyMenuFood(Long dailyId, Long foodId, String nameCategory );

    Boolean updateDaily(List<Long> idDeleteFoods, String note, Long id);

    DailyRes applyDailySuggest(DailyReq dailyReq);



}
