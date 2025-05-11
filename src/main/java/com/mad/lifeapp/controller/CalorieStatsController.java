package com.mad.lifeapp.controller;

import com.mad.lifeapp.dto.DailyCalorieStatsDTO;
import com.mad.lifeapp.service.CalorieStatsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CalorieStatsController {
    private final CalorieStatsService service;

    public CalorieStatsController(CalorieStatsService service) {
        this.service = service;
    }

    @GetMapping("/calories")
    public ResponseEntity<List<DailyCalorieStatsDTO>> getCalorieStats(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(service.getCaloriesBetween(startDate, endDate));
    }
}
