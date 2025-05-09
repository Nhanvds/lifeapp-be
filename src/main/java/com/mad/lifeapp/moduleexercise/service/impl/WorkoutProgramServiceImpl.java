package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.WorkoutProgram;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.WorkoutProgramRepository;
import com.mad.lifeapp.moduleexercise.service.WorkoutProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutProgramServiceImpl implements WorkoutProgramService {
    private final WorkoutProgramRepository repository;

    @Override
    public List<WorkoutProgram> findAll() {
        return repository.findAll();
    }

    @Override
    public WorkoutProgram findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id " + id));
    }

    @Override
    public WorkoutProgram create(WorkoutProgram program) {
        program.setId(null);
        return repository.save(program);
    }

    @Override
    public WorkoutProgram update(Integer id, WorkoutProgram program) {
        WorkoutProgram existing = findById(id);
        existing.setName(program.getName());
        existing.setTotalExercises(program.getTotalExercises());
        existing.setDuration(program.getDuration());
        existing.setImageUrl(program.getImageUrl());
        existing.setCategory(program.getCategory());
        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        WorkoutProgram existing = findById(id);
        repository.delete(existing);
    }
}
