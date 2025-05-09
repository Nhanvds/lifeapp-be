package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
