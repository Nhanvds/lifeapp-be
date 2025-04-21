package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.VerificationCodeRequest;
import com.mad.lifeapp.entity.VerificationCodeEntity;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;

public interface VerificationCodeService {

    VerificationCodeEntity createVerification(String email, boolean isRegistration) throws InvalidException;

    boolean verifyForgetPassword(String token, VerificationCodeRequest verificationCodeRequest) throws ParserTokenException, InvalidException, NotFoundException;

}
