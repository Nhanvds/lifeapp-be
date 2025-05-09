package com.mad.lifeapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ingredients")
public class IngredientsEntity extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "gram")
    private Float gram;


    @OneToMany(mappedBy = "ingredient",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FoodIngredientEntity> foodIngredients;
}
