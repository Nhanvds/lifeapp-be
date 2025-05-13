package com.mad.lifeapp.controller;

import com.mad.lifeapp.dto.request.NutritionRequestDTO;
import com.mad.lifeapp.dto.response.CaloDataDTO;
import com.mad.lifeapp.dto.response.MacrosDataDTO;
import com.mad.lifeapp.service.NutritionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    @GetMapping("/api/nutrition/calo")
    public CaloDataDTO getCaloData(@Valid @ModelAttribute NutritionRequestDTO request) {
        return nutritionService.getCaloData(request.getDay(), request.getUserId());
    }

    @GetMapping("/api/nutrition/macros")
    public MacrosDataDTO getMacrosData(@Valid @ModelAttribute NutritionRequestDTO request) {
        return nutritionService.getMacrosData(request.getDay(), request.getUserId());
    }
}