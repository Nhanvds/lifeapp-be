package com.mad.lifeapp.moduleexercise.service;



import com.mad.lifeapp.moduleexercise.dto.WorkoutProgramUpdateDto;
import com.mad.lifeapp.moduleexercise.entity.WorkoutProgram;

import java.util.List;

public interface WorkoutProgramService {
    List<WorkoutProgram> findAll();

    WorkoutProgram findById(Integer id);

    WorkoutProgram create(WorkoutProgram program);

    WorkoutProgram update(Integer id, WorkoutProgram program);

    WorkoutProgram partialUpdate(Integer id, WorkoutProgramUpdateDto dto);

    void delete(Integer id);
}
