package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.PasswordRequest;
import com.mad.lifeapp.dto.request.UserRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.dto.response.UserResponse;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.nimbusds.jose.JOSEException;

public interface UserService {

    UserResponse register(UserRequest userRequest) throws NotFoundException, InvalidException;
    TokenResponse login(UserRequest userRequest) throws NotFoundException, InvalidCredentialsException, JOSEException;

    UserResponse updateUserInfo(String token, UserRequest userRequest) throws ParserTokenException;
    boolean changePassword(PasswordRequest passwordRequest, String token) throws ParserTokenException;

    boolean resetPassword(String token, PasswordRequest passwordRequest) throws ParserTokenException;

}
