package com.mad.lifeapp.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "food_ingredients")
public class FoodIngredientEntity extends BaseEntity{

    @Column(name = "gram", nullable = false)
    private Float gram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private FoodEntity food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "igredient_id", nullable = false)
    private IngredientsEntity ingredient;


}
