package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram, Integer> {
}
