package com.mad.lifeapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "verification_codes")
public class VerificationCodeEntity extends BaseEntity{
    @Column(name = "code")
    private String code;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
}

