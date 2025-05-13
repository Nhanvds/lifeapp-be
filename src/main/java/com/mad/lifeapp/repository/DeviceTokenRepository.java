package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.DeviceTokenEntity;
import com.mad.lifeapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceTokenRepository extends JpaRepository<DeviceTokenEntity, Long> {
    Optional<DeviceTokenEntity> findByToken(String token);
    Optional<DeviceTokenEntity> findByUser(UserEntity user);
}
