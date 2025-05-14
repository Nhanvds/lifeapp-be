package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    // Tìm NotificationEntity theo userId
    @Query("SELECT n FROM NotificationEntity n WHERE n.user.id = :userId")
    Optional<NotificationEntity> findByUserId(Long userId);

    // Cập nhật thời gian thông báo cho userId
    @Modifying
    @Transactional
    @Query("UPDATE NotificationEntity n SET n.breakfastTime = :breakfastTime, n.lunchTime = :lunchTime, n.dinnerTime = :dinnerTime WHERE n.user.id = :userId")
    int updateNotificationTimes(Long userId, LocalTime breakfastTime, LocalTime lunchTime, LocalTime dinnerTime);
}