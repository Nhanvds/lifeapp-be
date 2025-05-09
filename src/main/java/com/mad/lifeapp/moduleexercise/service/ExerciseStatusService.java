package com.mad.lifeapp.moduleexercise.service;



import com.mad.lifeapp.moduleexercise.entity.ExerciseStatus;

import java.util.List;

public interface ExerciseStatusService {
    List<ExerciseStatus> findAll();

    ExerciseStatus findById(Integer workoutSetId, Integer exerciseId);

    ExerciseStatus create(ExerciseStatus status);

    ExerciseStatus update(Integer workoutSetId, Integer exerciseId, ExerciseStatus status);

    void delete(Integer workoutSetId, Integer exerciseId);
}
