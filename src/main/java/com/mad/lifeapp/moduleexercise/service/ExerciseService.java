package com.mad.lifeapp.moduleexercise.service;



import com.mad.lifeapp.moduleexercise.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    List<Exercise> findAll();

    Exercise findById(Integer id);

    Exercise create(Exercise exercise);

    Exercise update(Integer id, Exercise exercise);

    void delete(Integer id);
}
