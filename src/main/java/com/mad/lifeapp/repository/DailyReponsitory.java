package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.DailyMenuEntity;
import com.mad.lifeapp.entity.DailyMenuFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Set;


@Repository

public interface DailyReponsitory extends JpaRepository<DailyMenuEntity, Long> {


    @Query("select daily from DailyMenuEntity as daily where daily.day = :day ")
    DailyMenuEntity findByDay(LocalDate day);
}
