package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.UserHealthProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
     *
     * @param userId
     * @return Bản ghi mới nhất của user
     */
    @Query("""
                select u from UserHealthProfileEntity u
                where u.user.id = :userId
                order by u.createdAt desc
            """)
    Optional<UserHealthProfileEntity> findLatestByUserId(@Param("userId") Long userId);

}
