package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.MacrosDataDTO;
import com.mad.lifeapp.repository.MacrosDataRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class MacrosDataService {
    private final MacrosDataRepository repository;

    public MacrosDataService(MacrosDataRepository repository) {
        this.repository = repository;
    }

    public MacrosDataDTO getMacrosDataByDateAndUserId(LocalDate date, Long userId) {
        MacrosDataDTO data = repository.findMacrosDataByDateAndUserId(date, userId);
        return data != null ? data : new MacrosDataDTO(0.0, 0.0, 0.0, 0L);
    }
}