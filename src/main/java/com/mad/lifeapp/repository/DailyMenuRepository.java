package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.DailyMenuEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenuEntity, Long> {
    @EntityGraph(attributePaths = {"dailyMenuFoods.food"})
    List<DailyMenuEntity> findByUserIdAndDayBetween(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT dm.day, " +
            "SUM(CASE WHEN dmf.category = 'Breakfast' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END) as breakfastCalo, " +
            "SUM(CASE WHEN dmf.category = 'Lunch' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END) as lunchCalo, " +
            "SUM(CASE WHEN dmf.category = 'Dinner' THEN (f.carbs * 4 + f.fat * 9 + f.protein * 4) ELSE 0 END) as dinnerCalo " +
            "FROM DailyMenuEntity dm " +
            "JOIN dm.dailyMenuFoods dmf " +
            "JOIN dmf.food f " +
            "JOIN dm.user u " +
            "WHERE u.id = :userId AND dm.day BETWEEN :startDate AND :endDate " +
            "GROUP BY dm.day")
    List<Object[]> calculateCaloriesByDay(@Param("userId") Long userId,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);
}