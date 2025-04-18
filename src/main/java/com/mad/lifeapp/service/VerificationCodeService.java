package com.mad.lifeapp.service;

import com.mad.lifeapp.entity.VerificationCodeEntity;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;

public interface VerificationCodeService {

    VerificationCodeEntity createVerification(String email) throws InvalidException;



}
