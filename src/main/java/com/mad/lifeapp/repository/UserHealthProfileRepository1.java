package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.UserHealthProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserHealthProfileRepository1 extends JpaRepository<UserHealthProfileEntity, Long> {
    List<UserHealthProfileEntity> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}