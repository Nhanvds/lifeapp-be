package com.mad.lifeapp.moduleexercise.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "sleep_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SleepLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sleep_date", nullable = false)
    private LocalDate sleepDate;

    @Column(name = "target_hours", nullable = false)
    private Float targetHours;

    @Column(name = "actual_hours", nullable = false)
    private Float actualHours;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;
}

