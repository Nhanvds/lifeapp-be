package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.NoteFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface NoteFoodRepository extends JpaRepository<NoteFoodEntity, Long> {





}
