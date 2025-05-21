package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.AchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<AchievementEntity,Long> {
}
