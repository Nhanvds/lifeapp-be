package com.mad.lifeapp.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "daily_suggestion_foods")
public class DailySuggestionFoodEntity extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_suggestion_id", nullable = false)
    private DailySuggestionEntity dailySuggestions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private FoodEntity food;




}
