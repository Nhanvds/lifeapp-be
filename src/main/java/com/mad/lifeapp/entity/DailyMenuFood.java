package com.mad.lifeapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "daily_menu_foods")

public class DailyMenuFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
}
