package com.mad.lifeapp.controller;

import com.mad.lifeapp.dto.request.WeightQueryRequest;
import com.mad.lifeapp.dto.response.DailyWeightStats;
import com.mad.lifeapp.service.WeightStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weight-stats")
public class WeightStatsController {

    @Autowired
    private WeightStatsService weightStatsService;

    @GetMapping("/test")
    public String test() {
        return "Server is running!";
    }

    @GetMapping("/range")
    public List<DailyWeightStats> getWeightStatsByDateRange(
            @RequestParam("startDay") int startDay,
            @RequestParam("endDay") int endDay,
            @RequestParam("month") int month,
            @RequestParam("year") int year) {
        WeightQueryRequest request = new WeightQueryRequest(startDay, endDay, month, year);
        return weightStatsService.getWeightStatsByDateRange(request);
    }
}
