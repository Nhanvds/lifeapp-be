package com.mad.lifeapp.repository;

import com.mad.lifeapp.dto.DailyCalorieStatsDTO;
import com.mad.lifeapp.entity.DailyMenuFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DailyMenuFoodRepository extends JpaRepository<DailyMenuFood, Long> {

    @Query("SELECT new com.mad.lifeapp.dto.DailyCalorieStatsDTO(d.createdAt, " +
            "SUM(f.protein * 4 + f.carbs * 4 + f.fat * 9)) " +
            "FROM DailyMenuFood d JOIN d.food f " +
            "WHERE d.createdAt BETWEEN :start AND :end " +
            "GROUP BY d.createdAt ORDER BY d.createdAt")

    List<DailyCalorieStatsDTO> getDailyCaloriesBetween(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
}
