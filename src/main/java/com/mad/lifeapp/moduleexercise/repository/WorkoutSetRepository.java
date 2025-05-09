package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Integer> {
    List<WorkoutSet> findAllByUserId(Long userId);
    Optional<WorkoutSet> findByIdAndUserId(Integer id, Long userId);
}
