package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.DailyMenuEntity;
import com.mad.lifeapp.entity.UserHealthProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<DailyMenuEntity, Long> {

    List<DailyMenuEntity> findByUserIdAndDayBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<UserHealthProfileEntity> findByUserIdAndCreatedAtBetween(Long userId, LocalDate startDate, LocalDate endDate);
}