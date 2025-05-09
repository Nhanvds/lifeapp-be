package com.mad.lifeapp.moduleexercise.service;



import com.mad.lifeapp.moduleexercise.entity.SleepLog;

import java.util.List;

public interface SleepLogService {
    List<SleepLog> findAllByUser(Long userId);
    SleepLog findByIdForUser(Integer id, Long userId);
    void createAllForUser(List<SleepLog> logs, Long userId);
    SleepLog updateForUser(Integer id, SleepLog log, Long userId);
    void deleteForUser(Integer id, Long userId);
}

