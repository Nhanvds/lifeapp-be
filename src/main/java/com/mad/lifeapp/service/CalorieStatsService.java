package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.DailyCalorieStatsDTO;
import com.mad.lifeapp.repository.DailyMenuFoodRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalorieStatsService {
    private final DailyMenuFoodRepository repository;

    public CalorieStatsService(DailyMenuFoodRepository repository) {
        this.repository = repository;
    }

    public List<DailyCalorieStatsDTO> getCaloriesBetween(LocalDate start, LocalDate end) {
        return repository.getDailyCaloriesBetween(start, end);
    }
}
