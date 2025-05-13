package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.DeviceTokenRequest;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;

public interface DeviceTokenService {
    void saveOrUpdateToken(String token, DeviceTokenRequest deviceTokenRequest) throws ParserTokenException, NotFoundException;
    void deleteToken(String deviceToken) throws NotFoundException;
}
