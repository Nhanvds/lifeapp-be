package com.mad.lifeapp.moduleexercise.service;



import com.mad.lifeapp.moduleexercise.entity.StepLog;

import java.util.List;

public interface StepLogService {
    List<StepLog> findAllByUser(Long userId);
    StepLog findByIdForUser(Integer id, Long userId);
    void createAllForUser(List<StepLog> logs, Long userId);
    StepLog updateForUser(Integer id, StepLog log, Long userId);
    void deleteForUser(Integer id, Long userId);
}
