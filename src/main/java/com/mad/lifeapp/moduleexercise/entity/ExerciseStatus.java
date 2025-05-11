package com.mad.lifeapp.moduleexercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "exercise_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseStatus {
    @EmbeddedId
    private ExerciseStatusId id;

    @Enumerated(EnumType.STRING)
    private ExerciseState status;

    @Column(name = "actual_duration_seconds", nullable = false)
    private Integer actualDurationSeconds;
}


