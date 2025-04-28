package com.mad.lifeapp.entity;

import com.mad.lifeapp.enums.ActivityLevelEnum;
import com.mad.lifeapp.enums.GoalEnum;
import com.mad.lifeapp.enums.UserGenderEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "user_health_profiles")
public class UserHealthProfileEntity extends BaseEntity{

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGenderEnum gender;

    @Column(name = "height", nullable = false)
    private Float height;

    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "goal", nullable = false)
    @Enumerated(EnumType.STRING)
    private GoalEnum goal;

    @Column(name = "daily_calories_goal")
    private Float dailyCaloriesGoal;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_level")
    private ActivityLevelEnum activityLevel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "userHealthProfiles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DailySuggestionEntity> dailySuggestions;
}
