package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.response.StatisticsResponse;
import com.mad.lifeapp.entity.UserHealthProfileEntity;
import com.mad.lifeapp.repository.DailyMenuRepository;
import com.mad.lifeapp.repository.UserHealthProfileRepository1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    private DailyMenuRepository dailyMenuRepository;

    @Autowired
    private UserHealthProfileRepository1 userHealthProfileRepository1;

    public List<StatisticsResponse> getCaloriesAndWeightStats(Long userId, LocalDate startDate, LocalDate endDate) {
        logger.debug("Fetching stats for userId={}, startDate={}, endDate={}", userId, startDate, endDate);

        // Convert LocalDate to LocalDateTime for weight data
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        // Get calories using JPQL
        List<Object[]> calorieData = dailyMenuRepository.calculateCaloriesByDay(userId, startDate, endDate);
        logger.debug("Found {} days with calorie data", calorieData.size());

        Map<LocalDate, StatisticsResponse> statsByDay = calorieData.stream()
                .collect(Collectors.toMap(
                        data -> (LocalDate) data[0],
                        data -> {
                            StatisticsResponse stats = new StatisticsResponse();
                            stats.setDate((LocalDate) data[0]);
                            stats.setBreakfastCalo(((Number) data[1]).floatValue());
                            stats.setLunchCalo(((Number) data[2]).floatValue());
                            stats.setDinnerCalo(((Number) data[3]).floatValue());
                            stats.setTotalCalories(stats.getBreakfastCalo() + stats.getLunchCalo() + stats.getDinnerCalo());
                            return stats;
                        },
                        (v1, v2) -> v1
                ));

        // Get weight data
        List<UserHealthProfileEntity> healthProfiles = userHealthProfileRepository1
                .findByUserIdAndCreatedAtBetween(userId, startDateTime, endDateTime);
        logger.debug("Found {} health profiles", healthProfiles.size());

        Map<LocalDate, Float> weightByDay = healthProfiles.stream()
                .collect(Collectors.toMap(
                        profile -> profile.getCreatedAt().toLocalDate(),
                        UserHealthProfileEntity::getWeight,
                        (v1, v2) -> v1
                ));

        // Build response
        List<StatisticsResponse> response = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            StatisticsResponse stats = statsByDay.getOrDefault(date, new StatisticsResponse());
            stats.setDate(date);
            stats.setWeight(weightByDay.get(date));
            if (stats.getBreakfastCalo() == null) stats.setBreakfastCalo(0.0f);
            if (stats.getLunchCalo() == null) stats.setLunchCalo(0.0f);
            if (stats.getDinnerCalo() == null) stats.setDinnerCalo(0.0f);
            if (stats.getTotalCalories() == null) stats.setTotalCalories(0.0f);
            response.add(stats);
        }

        return response;
    }
}