package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.HeartRateLog;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.HeartRateLogRepository;
import com.mad.lifeapp.moduleexercise.service.HeartRateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartRateLogServiceImpl implements HeartRateLogService {
    private final HeartRateLogRepository repository;

    @Override
    public List<HeartRateLog> findAllByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public HeartRateLog findByIdForUser(Integer id, Long userId) {
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("HeartRateLog not found for user " + userId));
    }

    @Override
    @Transactional
    public void createAllForUser(List<HeartRateLog> logs, Long userId) {
        logs.forEach(log -> {
            log.setId(null);
            log.setUserId(userId);
        });
        repository.saveAll(logs);
    }

    @Override
    public HeartRateLog updateForUser(Integer id, HeartRateLog log, Long userId) {
        HeartRateLog existing = findByIdForUser(id, userId);
        existing.setValue(log.getValue());
        existing.setMeasuredAt(log.getMeasuredAt());
        return repository.save(existing);
    }

    @Override
    public void deleteForUser(Integer id, Long userId) {
        HeartRateLog existing = findByIdForUser(id, userId);
        repository.delete(existing);
    }
}
