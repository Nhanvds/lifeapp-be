package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.CaloDataDTO;
import com.mad.lifeapp.repository.CaloDataRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CaloDataService {
    private final CaloDataRepository repository;

    public CaloDataService(CaloDataRepository repository) {
        this.repository = repository;
    }

    public CaloDataDTO getCaloDataByDateAndUserId(LocalDate date, Long userId) {
        CaloDataDTO data = repository.findCaloDataByDateAndUserId(date, userId);
        return data != null ? data : new CaloDataDTO(0.0, 0.0, 0.0, 0L);
    }
}