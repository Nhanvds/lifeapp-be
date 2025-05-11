package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.SleepLog;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.SleepLogRepository;
import com.mad.lifeapp.moduleexercise.service.SleepLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SleepLogServiceImpl implements SleepLogService {
    private final SleepLogRepository repository;

    @Override
    public List<SleepLog> findAllByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public SleepLog findByIdForUser(Integer id, Long userId) {
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("SleepLog not found for user " + userId));
    }

    @Override
    @Transactional
    public void createAllForUser(List<SleepLog> logs, Long userId) {
        logs.forEach(log -> { log.setId(null); log.setUserId(userId); });
        repository.saveAll(logs);
    }

    @Override
    public SleepLog updateForUser(Integer id, SleepLog log, Long userId) {
        SleepLog existing = findByIdForUser(id, userId);
        existing.setSleepDate(log.getSleepDate());
        existing.setTargetHours(log.getTargetHours());
        existing.setActualHours(log.getActualHours());
        return repository.save(existing);
    }

    @Override
    public void deleteForUser(Integer id, Long userId) {
        SleepLog existing = findByIdForUser(id, userId);
        repository.delete(existing);
    }
}

