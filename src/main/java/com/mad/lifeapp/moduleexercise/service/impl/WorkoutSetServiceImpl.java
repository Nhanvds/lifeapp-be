package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.WorkoutSet;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.WorkoutSetRepository;
import com.mad.lifeapp.moduleexercise.service.WorkoutSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutSetServiceImpl implements WorkoutSetService {
    private final WorkoutSetRepository repository;

    @Override
    public List<WorkoutSet> findAllByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public WorkoutSet findByIdForUser(Integer id, Long userId) {
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutSet not found for user " + userId));
    }

    @Override
    public WorkoutSet createForUser(WorkoutSet set, Long userId) {
        set.setId(null);
        set.setUserId(userId);
        return repository.save(set);
    }

    @Override
    public WorkoutSet updateForUser(Integer id, WorkoutSet set, Long userId) {
        WorkoutSet existing = findByIdForUser(id, userId);
        existing.setName(set.getName());
        existing.setStartTime(set.getStartTime());
        existing.setEndTime(set.getEndTime());
        existing.setCurrentExerciseIndex(set.getCurrentExerciseIndex());
        existing.setProgram(set.getProgram());
        return repository.save(existing);
    }

    @Override
    public void deleteForUser(Integer id, Long userId) {
        WorkoutSet existing = findByIdForUser(id, userId);
        repository.delete(existing);
    }
}
