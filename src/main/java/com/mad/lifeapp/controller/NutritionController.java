package com.mad.lifeapp.controller;

import com.mad.lifeapp.service.CaloDataService;
import com.mad.lifeapp.service.MacrosDataService;
import com.mad.lifeapp.dto.CaloDataDTO;
import com.mad.lifeapp.dto.MacrosDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionController {
    private final CaloDataService caloDataService;
    private final MacrosDataService macrosDataService;

    public NutritionController(CaloDataService caloDataService, MacrosDataService macrosDataService) {
        this.caloDataService = caloDataService;
        this.macrosDataService = macrosDataService;
    }

    @GetMapping("/calo/{date}")
    public ResponseEntity<CaloDataDTO> getCaloData(
            @PathVariable String date,
            @RequestParam Long userId) {
        LocalDate localDate = LocalDate.parse(date);
        CaloDataDTO data = caloDataService.getCaloDataByDateAndUserId(localDate, userId);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/macros/{date}")
    public ResponseEntity<MacrosDataDTO> getMacrosData(
            @PathVariable String date,
            @RequestParam Long userId) {
        LocalDate localDate = LocalDate.parse(date);
        MacrosDataDTO data = macrosDataService.getMacrosDataByDateAndUserId(localDate, userId);
        return ResponseEntity.ok(data);
    }
}