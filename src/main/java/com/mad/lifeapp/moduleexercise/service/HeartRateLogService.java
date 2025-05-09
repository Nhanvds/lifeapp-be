package com.mad.lifeapp.moduleexercise.service;



import com.mad.lifeapp.moduleexercise.entity.HeartRateLog;

import java.util.List;

public interface HeartRateLogService {
    List<HeartRateLog> findAllByUser(Long userId);
    HeartRateLog findByIdForUser(Integer id, Long userId);
    void createAllForUser(List<HeartRateLog> logs, Long userId);
    HeartRateLog updateForUser(Integer id, HeartRateLog log, Long userId);
    void deleteForUser(Integer id, Long userId);
}
