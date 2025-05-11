package com.mad.lifeapp.moduleexercise.repository;


import com.mad.lifeapp.moduleexercise.entity.ProgramExerciseOrder;
import com.mad.lifeapp.moduleexercise.entity.ProgramExerciseOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramExerciseOrderRepository extends JpaRepository<ProgramExerciseOrder, ProgramExerciseOrderId> {
}
