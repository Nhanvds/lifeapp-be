package com.mad.lifeapp.moduleexercise.repository;

import com.mad.lifeapp.moduleexercise.entity.SleepLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SleepLogRepository extends JpaRepository<SleepLog, Integer> {
    List<SleepLog> findAllByUserId(Long userId);
    Optional<SleepLog> findByIdAndUserId(Integer id, Long userId);
}
