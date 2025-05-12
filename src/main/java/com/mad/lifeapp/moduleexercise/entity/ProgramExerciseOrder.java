package com.mad.lifeapp.moduleexercise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "program_exercise_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramExerciseOrder {
    @EmbeddedId
    private ProgramExerciseOrderId id;

    @Column(name = "order_number", nullable = false)
    private Integer order;
}

