package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.FoodEntity;
import com.mad.lifeapp.enums.CategoryEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodReponsitory extends JpaRepository<FoodEntity, Long> {

    Page<FoodEntity> findByCategory(@Param("category") CategoryEnum category, Pageable pageable);

}
