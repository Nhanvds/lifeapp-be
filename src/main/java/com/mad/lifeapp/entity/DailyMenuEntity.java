package com.mad.lifeapp.entity;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "daily_menus")
public class DailyMenuEntity extends BaseEntity{
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "food_id",nullable = false)
    private FoodEntity food;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;
}
