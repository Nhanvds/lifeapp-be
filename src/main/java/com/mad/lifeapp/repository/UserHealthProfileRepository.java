package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.UserHealthProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UserHealthProfileRepository extends JpaRepository<UserHealthProfileEntity, Long> {


    /**
     * @return Bản ghi trong ngày tìm kiếm
     */
    @Query("""
                select u from UserHealthProfileEntity u
                where u.user.id = :userId and u.createdAt BETWEEN :startOfDay AND :endOfDay
            """)
    Optional<UserHealthProfileEntity> findLatestByUserId(@Param("userId") Long userId,
                                                         @Param("startOfDay") LocalDateTime startOfDay,
                                                         @Param("endOfDay") LocalDateTime endOfDay);


    /**
     * @param userId id của user
     * @return Bản ghi mới nhất của user
     */
    @Query(value = """
                SELECT * FROM user_health_profiles u
                WHERE u.user_id = :userId
                ORDER BY u.created_at DESC
                LIMIT 1
            """, nativeQuery = true)
    Optional<UserHealthProfileEntity> findLatestByUserId(@Param("userId") Long userId);


    /**
     * @param date   ngày cần lấy thông tin
     * @param userId id của user
     * @return Bản ghi mới nhất của user có thời gian tạo mới nhất <= date
     */
    @Query(value = """
                SELECT * FROM user_health_profiles u
                WHERE u.user_id = :userId AND u.created_at <= :date
                ORDER BY u.created_at DESC
                LIMIT 1
            """, nativeQuery = true)
    Optional<UserHealthProfileEntity> findLatestByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDateTime date);


}
