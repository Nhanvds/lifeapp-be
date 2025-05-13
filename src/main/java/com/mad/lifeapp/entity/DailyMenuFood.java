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

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    private String category;

    @Column(name = "daily_menu_id")
    private Long dailyMenuId;

    @ManyToOne
    @JoinColumn(name = "daily_menu_id", insertable = false, updatable = false)
    private DailyMenu dailyMenu;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
}
