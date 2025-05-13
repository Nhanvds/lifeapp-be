package com.mad.lifeapp.repository;

import com.mad.lifeapp.dto.MacrosDataDTO;
import com.mad.lifeapp.entity.DailyMenuFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface MacrosDataRepository extends JpaRepository<DailyMenuFood, Long> {
    @Query("SELECT new com.mad.lifeapp.dto.MacrosDataDTO(" +
            "SUM(f.protein), " +
            "SUM(f.carbs), " +
            "SUM(f.fat), " +
            "dm.userHealthProfile.dailyCaloriesGoal) " +
            "FROM DailyMenuFood dmf " +
            "JOIN dmf.food f " +
            "JOIN dmf.dailyMenu dm " +
            "WHERE dmf.createdAt = :date AND dm.userId = :userId " +
            "GROUP BY dm.userHealthProfile.dailyCaloriesGoal")
    MacrosDataDTO findMacrosDataByDateAndUserId(@Param("date") LocalDate date, @Param("userId") Long userId);
}