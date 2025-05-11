package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.response.DailyRes;

import java.time.LocalDate;
import java.util.List;

public interface DailyService {
    DailyRes getDailyMenuFoodRes(LocalDate localDate, Long idUser);

    Boolean addDailyMenuFood(Long dailyId, Long foodId, String nameCategory );

    Boolean updateDaily(List<Long> idFoods, String note, Long id);


}
