package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodReponsitory extends JpaRepository<FoodEntity, Long> {
}
