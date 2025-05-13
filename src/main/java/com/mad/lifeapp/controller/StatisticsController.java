package com.mad.lifeapp.controller;


import com.mad.lifeapp.dto.response.StatisticsResponse;
import com.mad.lifeapp.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/calories-and-weight")
    public ResponseEntity<?> getCaloriesAndWeightStats(
            @RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            if (userId == null || startDate == null || endDate == null || startDate.isAfter(endDate)) {
                return ResponseEntity.badRequest().body("Invalid input: userId, startDate, and endDate must be valid, and startDate must not be after endDate.");
            }
            List<StatisticsResponse> stats = statisticsService.getCaloriesAndWeightStats(userId, startDate, endDate);
            if (stats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for userId " + userId + " in the given date range.");
            }
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}