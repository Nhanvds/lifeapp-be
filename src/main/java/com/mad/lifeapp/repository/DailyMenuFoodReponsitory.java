package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.DailyMenuEntity;
import com.mad.lifeapp.entity.DailyMenuFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository

public interface DailyMenuFoodReponsitory extends JpaRepository<DailyMenuFoodEntity, Long> {

    @Modifying
    @Query("DELETE from DailyMenuFoodEntity daily_menu_food where daily_menu_food.id = :idDailyMenuFood")
    void deleteByIdDailyMenuFood(@Param("idDailyMenuFood") Long idDailyMenuFood);


    @Modifying
    void deleteByDailyMenuEntity(DailyMenuEntity dailyMenuEntity);

}
