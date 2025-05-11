package com.mad.lifeapp.dto.response;

import java.time.LocalDateTime;

public class DailyWeightStats {
    private Long id;
    private LocalDateTime createdAt;
    private Float weight;

    public DailyWeightStats(Long id, LocalDateTime createdAt, Float weight) {
        this.id = id;
        this.createdAt = createdAt;
        this.weight = weight;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
