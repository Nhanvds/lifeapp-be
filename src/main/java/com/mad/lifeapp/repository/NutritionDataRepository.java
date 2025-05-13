package com.mad.lifeapp.repository;

import com.mad.lifeapp.dto.response.CaloDataDTO;
import com.mad.lifeapp.dto.response.MacrosDataDTO;
import com.mad.lifeapp.entity.DailyMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface NutritionDataRepository extends JpaRepository<DailyMenuEntity, Long> {

    @Query("SELECT new com.mad.lifeapp.dto.response.CaloDataDTO(" +
            "SUM(CASE WHEN dmf.category = 'Breakfast' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END), " +
            "SUM(CASE WHEN dmf.category = 'Lunch' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END), " +
            "SUM(CASE WHEN dmf.category = 'Dinner' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END), " +
            "uhp.dailyCaloriesGoal) " +
            "FROM DailyMenuEntity dm " +
            "JOIN dm.dailyMenuFoods dmf " +
            "JOIN dmf.food f " +
            "JOIN dm.user u " +
            "JOIN u.userHealthProfiles uhp " +
            "WHERE dm.day = :date AND u.id = :userId " +
            "AND uhp.createdAt = (SELECT MAX(uhp2.createdAt) FROM UserHealthProfileEntity uhp2 WHERE uhp2.user.id = u.id) " +
            "GROUP BY uhp.dailyCaloriesGoal")
    CaloDataDTO findCaloDataByDateAndUserId(@Param("date") LocalDate date, @Param("userId") Long userId);

    @Query("SELECT new com.mad.lifeapp.dto.response.MacrosDataDTO(" +
            "SUM(f.protein), " +
            "SUM(f.carbs), " +
            "SUM(f.fat), " +
            "uhp.dailyCaloriesGoal) " +
            "FROM DailyMenuEntity dm " +
            "JOIN dm.dailyMenuFoods dmf " +
            "JOIN dmf.food f " +
            "JOIN dm.user u " +
            "JOIN u.userHealthProfiles uhp " +
            "WHERE dm.day = :date AND u.id = :userId " +
            "AND uhp.createdAt = (SELECT MAX(uhp2.createdAt) FROM UserHealthProfileEntity uhp2 WHERE uhp2.user.id = u.id) " +
            "GROUP BY uhp.dailyCaloriesGoal")
    MacrosDataDTO findMacrosDataByDateAndUserId(@Param("date") LocalDate date, @Param("userId") Long userId);
}