package com.mad.lifeapp.repository;

import com.mad.lifeapp.entity.VerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCodeEntity,Long> {

    Optional<VerificationCodeEntity> findByEmail(String email);
}
