package com.mad.lifeapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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

    @Column(name = "day")
    private LocalDate day;

    @OneToMany(mappedBy ="dailyMenuEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DailyMenuFoodEntity> dailyMenuFoods;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;
}
