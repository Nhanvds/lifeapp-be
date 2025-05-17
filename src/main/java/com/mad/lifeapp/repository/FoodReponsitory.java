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

    Page<FoodEntity> findByCategory(CategoryEnum category, Pageable pageable);

    @Query("""
    select foods from FoodEntity as foods 
    where 
      foods.name like %:name% 
      and CAST(foods.category AS string) like %:category%
      and ABS(foods.cookingTime - :time) < 10
      and (foods.protein * 4 + foods.carbs * 4 + foods.fat * 9)  <= :endCalo
      and (foods.protein * 4 + foods.carbs * 4 + foods.fat * 9)  >= :startCalo
""")
    Page<FoodEntity> findNameTime(
            @Param("name") String name,
            @Param("time") int time,
            @Param("category") String category,
            @Param("startCalo") int startCalo,
            @Param("endCalo") int endCalo,
            Pageable pageable);

    @Query("""
    select foods from FoodEntity as foods 
    where 
      foods.name like %:name% 
      and CAST(foods.category AS string) like %:category%
      and (foods.protein * 4 + foods.carbs * 4 + foods.fat * 9)  <= :endCalo
      and (foods.protein * 4 + foods.carbs * 4 + foods.fat * 9)  >= :startCalo
""")
    Page<FoodEntity> findNameNoTime(
            @Param("name") String name,
            @Param("category") String category,
            @Param("startCalo") int startCalo,
            @Param("endCalo") int endCalo,
            Pageable pageable);
    @Query("select foods from FoodEntity as foods where foods.name like %:name%")
    Page<FoodEntity> findAllFood(@Param("name") String name, Pageable pageable);


}
