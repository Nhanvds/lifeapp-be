package com.mad.lifeapp.moduleexercise.service;



import com.mad.lifeapp.moduleexercise.entity.ProgramExerciseOrder;

import java.util.List;

public interface ProgramExerciseOrderService {
    List<ProgramExerciseOrder> findAll();

    ProgramExerciseOrder findById(Integer programId, Integer exerciseId);

    ProgramExerciseOrder create(ProgramExerciseOrder order);

    ProgramExerciseOrder update(Integer programId, Integer exerciseId, ProgramExerciseOrder order);

    void delete(Integer programId, Integer exerciseId);
}
