package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.ExerciseStatus;
import com.mad.lifeapp.moduleexercise.entity.ExerciseStatusId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseStatusRepository extends JpaRepository<ExerciseStatus, ExerciseStatusId> {
}
