package com.mad.lifeapp.moduleexercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

    @Column(nullable = false)
    private Integer calories;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}

