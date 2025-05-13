package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface INotificationRepository extends JpaRepository<NotificationEntity,Long> {

    @Query("select n from NotificationEntity n where n.user.id = :userId")
    Optional<NotificationEntity> findByUserId(Long userId);
}
