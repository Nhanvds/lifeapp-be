package com.mad.lifeapp.repository;

import com.mad.lifeapp.dto.CaloDataDTO;
import com.mad.lifeapp.entity.DailyMenuFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface CaloDataRepository extends JpaRepository<DailyMenuFood, Long> {
    @Query("SELECT new com.mad.lifeapp.dto.CaloDataDTO(" +
            "SUM(CASE WHEN dmf.category = 'Breakfast' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END), " +
            "SUM(CASE WHEN dmf.category = 'Lunch' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END), " +
            "SUM(CASE WHEN dmf.category = 'Dinner' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END), " +
            "dm.userHealthProfile.dailyCaloriesGoal) " +
            "FROM DailyMenuFood dmf " +
            "JOIN dmf.food f " +
            "JOIN dmf.dailyMenu dm " +
            "WHERE dmf.createdAt = :date AND dm.userId = :userId " +
            "GROUP BY dm.userHealthProfile.dailyCaloriesGoal")
    CaloDataDTO findCaloDataByDateAndUserId(@Param("date") LocalDate date, @Param("userId") Long userId);
}