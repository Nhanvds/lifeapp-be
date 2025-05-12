package com.mad.lifeapp.moduleexercise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "heart_rate_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeartRateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer value;

    @Column(name = "measured_at", nullable = false)
    private LocalDateTime measuredAt;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;
}

