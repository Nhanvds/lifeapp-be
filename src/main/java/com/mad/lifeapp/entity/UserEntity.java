package com.mad.lifeapp.entity;

import com.mad.lifeapp.enums.UserRoleEnum;
import com.mad.lifeapp.enums.UserStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "country")
    private String country;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(name = "google_id")
    private String googleId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private NotificationEntity notification;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AchievementEntity> achievementEntities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserHealthProfileEntity> userHealthProfiles;

}
