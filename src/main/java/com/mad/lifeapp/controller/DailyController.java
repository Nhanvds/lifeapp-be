package com.mad.lifeapp.controller;


import com.mad.lifeapp.dto.response.DailyRes;
import com.mad.lifeapp.service.DailyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/dailys")
@RequiredArgsConstructor
public class DailyController {

    private final DailyService dailyService;

    @GetMapping("/daily")
    ResponseEntity<DailyRes> getDaily(@RequestParam("localDate") LocalDate localDate){
        return ResponseEntity.ok().body(dailyService.getDailyMenuFoodRes(localDate, 3L));
    }

    @PostMapping("/add-food-daily")
    ResponseEntity<Boolean> addFoodDaily(@RequestParam("dailyId") Long dailyId, @RequestParam("foodId") Long foodId,@RequestParam("nameCategory")String nameCategory ){
        return ResponseEntity.ok().body(dailyService.addDailyMenuFood(dailyId, foodId, nameCategory));
    }

    @PostMapping("/delete-food-daily")
    ResponseEntity<Boolean> updateDaily(@RequestBody List<Long> idFoods, @RequestParam String note, @RequestParam Long id){
        return ResponseEntity.ok().body(dailyService.updateDaily(idFoods, note, id));
    }
}
