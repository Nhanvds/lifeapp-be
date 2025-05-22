package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.FoodEntity;
import com.mad.lifeapp.entity.NoteFoodEntity;
import com.mad.lifeapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface NoteFoodRepository extends JpaRepository<NoteFoodEntity, Long> {
    NoteFoodEntity findByUserAndFood(UserEntity user, FoodEntity food);
}
