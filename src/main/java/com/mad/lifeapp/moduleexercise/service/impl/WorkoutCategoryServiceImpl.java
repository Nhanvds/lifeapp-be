package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.WorkoutCategory;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.WorkoutCategoryRepository;
import com.mad.lifeapp.moduleexercise.service.WorkoutCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutCategoryServiceImpl implements WorkoutCategoryService {
    private final WorkoutCategoryRepository repo;

    @Override
    public List<WorkoutCategory> findAll() {
        return repo.findAll();
    }

    @Override
    public WorkoutCategory findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
    }

    @Override
    public WorkoutCategory create(WorkoutCategory category) {
        category.setId(null);
        return repo.save(category);
    }

    @Override
    public WorkoutCategory update(Integer id, WorkoutCategory category) {
        WorkoutCategory existing = findById(id);
        existing.setName(category.getName());
        return repo.save(existing);
    }

    @Override
    public void delete(Integer id) {
        WorkoutCategory existing = findById(id);
        repo.delete(existing);
    }
}
