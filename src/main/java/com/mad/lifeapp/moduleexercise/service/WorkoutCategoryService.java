package com.mad.lifeapp.moduleexercise.service;



import com.mad.lifeapp.moduleexercise.entity.WorkoutCategory;

import java.util.List;

public interface WorkoutCategoryService {
    List<WorkoutCategory> findAll();
    WorkoutCategory findById(Integer id);
    WorkoutCategory create(WorkoutCategory category);
    WorkoutCategory update(Integer id, WorkoutCategory category);
    void delete(Integer id);
}
