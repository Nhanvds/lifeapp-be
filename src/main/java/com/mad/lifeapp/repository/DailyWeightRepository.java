package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.UserHealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DailyWeightRepository extends JpaRepository<UserHealthProfile, Long> {

    @Query("SELECT u FROM UserHealthProfile u WHERE " +
            "DAY(u.createdAt) BETWEEN :startDay AND :endDay " +
            "AND MONTH(u.createdAt) = :month " +
            "AND YEAR(u.createdAt) = :year")
    List<UserHealthProfile> findByDateRange(
            @Param("startDay") int startDay,
            @Param("endDay") int endDay,
            @Param("month") int month,
            @Param("year") int year);
}
