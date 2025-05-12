package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.StepLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StepLogRepository extends JpaRepository<StepLog, Integer> {
    List<StepLog> findAllByUserId(Long userId);
    Optional<StepLog> findByIdAndUserId(Integer id, Long userId);
}
