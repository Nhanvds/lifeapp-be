package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.StepLog;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.StepLogRepository;
import com.mad.lifeapp.moduleexercise.service.StepLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepLogServiceImpl implements StepLogService {
    private final StepLogRepository repository;

    @Override
    public List<StepLog> findAllByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public StepLog findByIdForUser(Integer id, Long userId) {
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("StepLog not found for user " + userId));
    }

    @Override
    @Transactional
    public void createAllForUser(List<StepLog> logs, Long userId) {
        logs.forEach(log -> {
            log.setId(null);
            log.setUserId(userId);
        });
        repository.saveAll(logs);
    }

    @Override
    public StepLog updateForUser(Integer id, StepLog log, Long userId) {
        StepLog existing = findByIdForUser(id, userId);
        existing.setStartTime(log.getStartTime());
        existing.setEndTime(log.getEndTime());
        existing.setAmount(log.getAmount());
        return repository.save(existing);
    }

    @Override
    public void deleteForUser(Integer id, Long userId) {
        StepLog existing = findByIdForUser(id, userId);
        repository.delete(existing);
    }
}
