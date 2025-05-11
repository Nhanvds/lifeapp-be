package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.FoodEntity;
import com.mad.lifeapp.entity.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IngredientReponsitory extends JpaRepository<IngredientsEntity, Long> {
}
