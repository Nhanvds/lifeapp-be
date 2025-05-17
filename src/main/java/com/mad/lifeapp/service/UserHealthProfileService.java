package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.UserHealthProfileRequest;
import com.mad.lifeapp.dto.response.UserHealthProfileResponse;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.exception.UserNotFoundException;

public interface UserHealthProfileService {

    UserHealthProfileResponse createUserHealthProfile(UserHealthProfileRequest userHealthProfileRequest, String token) throws ParserTokenException, UserNotFoundException;
    UserHealthProfileResponse getUserHealthProfile(String token) throws ParserTokenException;
}
