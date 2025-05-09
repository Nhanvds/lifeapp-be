package com.mad.lifeapp.entity;

import com.mad.lifeapp.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "foods")
public class FoodEntity extends BaseEntity {

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cooking_time",nullable = false)
    private Integer cookingTime;

    @Column(name = "level",nullable = false)
    private String level;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "protein",nullable = false)
    private Float protein;

    @Column(name = "fat",nullable = false)
    private Float fat;

    @Column(name = "carbs",nullable = false)
    private Float carbs;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private CategoryEnum category;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<NoteFoodEntity> noteFoods;

    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<FoodIngredientEntity> foodIngredients;

    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DailyMenuEntity> dailyMenus;

    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DailySuggestionFoodEntity> dailySuggestionFoods;

}
