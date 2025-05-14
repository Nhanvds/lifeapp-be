package com.mad.lifeapp.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.mad.lifeapp.dto.request.FirebaseTokenRequest;
import com.mad.lifeapp.dto.request.PasswordRequest;
import com.mad.lifeapp.dto.request.UserRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.dto.response.UserResponse;
import com.mad.lifeapp.exception.*;
import com.nimbusds.jose.JOSEException;

public interface UserService {

    UserResponse register(UserRequest userRequest) throws NotFoundException, InvalidException;
    TokenResponse login(UserRequest userRequest) throws NotFoundException, InvalidCredentialsException, JOSEException;

    TokenResponse loginWithFirebase(FirebaseTokenRequest firebaseTokenRequest) throws FirebaseAuthException, JOSEException;

    UserResponse updateUserInfo(String token, UserRequest userRequest) throws ParserTokenException, UserNotFoundException;
    boolean changePassword(PasswordRequest passwordRequest, String token) throws ParserTokenException, UserNotFoundException;

    boolean resetPassword(String token, PasswordRequest passwordRequest) throws ParserTokenException, UserNotFoundException;

    UserResponse getUser(String token) throws ParserTokenException;
}
