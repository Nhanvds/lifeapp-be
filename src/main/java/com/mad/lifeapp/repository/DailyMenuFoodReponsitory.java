package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.DailyMenuEntity;
import com.mad.lifeapp.entity.DailyMenuFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Repository

public interface DailyMenuFoodReponsitory extends JpaRepository<DailyMenuFoodEntity, Long> {





}
