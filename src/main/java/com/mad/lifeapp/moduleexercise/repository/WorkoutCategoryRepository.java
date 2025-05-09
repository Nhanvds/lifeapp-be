package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.WorkoutCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutCategoryRepository extends JpaRepository<WorkoutCategory, Integer> {
}
