package com.mad.lifeapp.entity;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "daily_menu_foods")
public class DailyMenuFoodEntity extends BaseEntity{
    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "daily_menu_id",nullable = false)
    private DailyMenuEntity dailyMenuEntity;

    @ManyToOne
    @JoinColumn(name = "food_id",nullable = false)
    private FoodEntity food;
}
