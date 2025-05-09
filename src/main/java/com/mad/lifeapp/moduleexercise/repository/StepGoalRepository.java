package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.StepGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepGoalRepository extends JpaRepository<StepGoal, Long> {
}