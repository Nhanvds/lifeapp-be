package com.mad.lifeapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity{

    @Column(name = "breakfast_time")
    private LocalDateTime breakfastTime;

    @Column(name = "lunch_time")
    private LocalDateTime lunchTime;

    @Column(name = "dinner_time")
    private LocalDateTime dinnerTime;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
