package com.mad.lifeapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "daily_suggestions")
public class DailySuggestionEntity extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userHealthProfileEntity_id", nullable = false)
    private UserHealthProfileEntity userHealthProfiles;


    @OneToMany(mappedBy = "dailySuggestions",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<DailySuggestionFoodEntity> dailySuggestionFoods;



}
