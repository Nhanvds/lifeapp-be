package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.VerificationCodeRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.entity.VerificationCodeEntity;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.nimbusds.jose.JOSEException;

public interface VerificationCodeService {

    VerificationCodeEntity createVerification(String email, boolean isRegistration) throws InvalidException;

    TokenResponse verifyForgetPassword(VerificationCodeRequest verificationCodeRequest) throws ParserTokenException, InvalidException, NotFoundException, JOSEException;

}
