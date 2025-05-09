package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.Exercise;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.ExerciseRepository;
import com.mad.lifeapp.moduleexercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository repository;

    @Override
    public List<Exercise> findAll() {
        return repository.findAll();
    }

    @Override
    public Exercise findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id " + id));
    }

    @Override
    public Exercise create(Exercise exercise) {
        exercise.setId(null);
        return repository.save(exercise);
    }

    @Override
    public Exercise update(Integer id, Exercise exercise) {
        Exercise existing = findById(id);
        existing.setName(exercise.getName());
        existing.setDescription(exercise.getDescription());
        existing.setDurationSeconds(exercise.getDurationSeconds());
        existing.setCalories(exercise.getCalories());
        existing.setImageUrl(exercise.getImageUrl());
        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        Exercise existing = findById(id);
        repository.delete(existing);
    }
}