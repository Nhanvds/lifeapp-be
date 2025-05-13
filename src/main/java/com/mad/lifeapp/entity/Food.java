package com.mad.lifeapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table (name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private Float carbs;

    private String category;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Column(name = "created_by")
    private Long createdBy;

    private String description;

    private Float fat;

    @Column(name = "image_url")
    private String imageUrl;

    private String level;

    private String name;

    private Float protein;
}
