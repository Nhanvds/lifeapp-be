package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.response.CaloDataDTO;
import com.mad.lifeapp.dto.response.MacrosDataDTO;
import com.mad.lifeapp.repository.NutritionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NutritionService {

    @Autowired
    private NutritionDataRepository nutritionDataRepository;

    public CaloDataDTO getCaloData(LocalDate date, Long userId) {
        CaloDataDTO caloData = nutritionDataRepository.findCaloDataByDateAndUserId(date, userId);
        return caloData != null ? caloData : new CaloDataDTO(0.0, 0.0, 0.0, 0.0f);
    }

    public MacrosDataDTO getMacrosData(LocalDate date, Long userId) {
        MacrosDataDTO macrosData = nutritionDataRepository.findMacrosDataByDateAndUserId(date, userId);
        return macrosData != null ? macrosData : new MacrosDataDTO(0.0, 0.0, 0.0, 0.0f);
    }
}