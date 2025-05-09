package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.HeartRateLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRateLogRepository extends JpaRepository<HeartRateLog, Integer> {
    List<HeartRateLog> findAllByUserId(Long userId);
    Optional<HeartRateLog> findByIdAndUserId(Integer id, Long userId);
}
