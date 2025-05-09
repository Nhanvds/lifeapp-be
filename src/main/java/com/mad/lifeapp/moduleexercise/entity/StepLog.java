package com.mad.lifeapp.moduleexercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "step_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Integer amount;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;
}

