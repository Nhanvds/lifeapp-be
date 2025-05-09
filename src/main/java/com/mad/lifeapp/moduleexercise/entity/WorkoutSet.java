package com.mad.lifeapp.moduleexercise.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "workout_sets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private WorkoutProgram program;

    @Column(nullable = false)
    private String name;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "current_exercise_index", nullable = false)
    private Integer currentExerciseIndex;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;
}


