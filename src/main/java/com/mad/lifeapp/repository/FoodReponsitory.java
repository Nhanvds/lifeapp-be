package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.FoodEntity;
import com.mad.lifeapp.enums.CategoryEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FoodReponsitory extends JpaRepository<FoodEntity, Long> {

    @Query("""
    select foods from FoodEntity as foods 
    where 
      CAST(foods.category AS string) like %:category%
      and (foods.createdBy = 0 or foods.createdBy = :idUser)
""")
    Page<FoodEntity> findByCategoryAndCreatedByOrCreatedBy(
            @Param("category") String category,
            @Param("idUser") Long idUser, Pageable pageable);

    @Query("""
    select foods from FoodEntity as foods 
    where 
      foods.name like %:name% 
      and CAST(foods.category AS string) like %:category%
      and ABS(foods.cookingTime - :time) < 10
      and (foods.protein * 4 + foods.carbs * 4 + foods.fat * 9)  <= :endCalo
      and (foods.protein * 4 + foods.carbs * 4 + foods.fat * 9)  >= :startCalo
      and (foods.createdBy = 0 or foods.createdBy = :idUser)
""")
    Page<FoodEntity> findNameTime(
            @Param("name") String name,
            @Param("time") int time,
            @Param("category") String category,
            @Param("startCalo") int startCalo,
            @Param("endCalo") int endCalo,
            @Param("idUser") Long idUser,
            Pageable pageable);

    @Query("""
    select foods from FoodEntity as foods 
    where 
      foods.name like %:name% 
      and CAST(foods.category AS string) like %:category%
      and (foods.protein * 4 + foods.carbs * 4 + foods.fat * 9)  <= :endCalo
      and (foods.protein * 4 + foods.carbs * 4 + foods.fat * 9)  >= :startCalo
      and (foods.createdBy = 0 or foods.createdBy = :idUser)
""")
    Page<FoodEntity> findNameNoTime(
            @Param("name") String name,
            @Param("category") String category,
            @Param("startCalo") int startCalo,
            @Param("endCalo") int endCalo,
            @Param("idUser") Long idUser,

            Pageable pageable);
    @Query("select foods from FoodEntity as foods where foods.name like %:name% and (foods.createdBy = 0 or foods.createdBy = :idUser)")
    Page<FoodEntity> findAllFood(@Param("name") String name, @Param("idUser") Long idUser, Pageable pageable);


    @Query("select food from FoodEntity food where food.id = :id and (food.createdBy = 0 or food.createdBy = :idUser)  ")
    Optional<FoodEntity> findById(@Param("idUser") Long idUser, @Param("id") Long id);


    List<FoodEntity> findByCreatedBy(Long idUser);


    @Query("select food from FoodEntity food where  CAST( food.category as string ) like %:category% ")
    List<FoodEntity> findByCategory(String category);


}
