package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.component.JwtUtils;
import com.mad.lifeapp.dto.request.DeviceTokenRequest;
import com.mad.lifeapp.entity.DeviceTokenEntity;
import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.repository.DeviceTokenRepository;
import com.mad.lifeapp.repository.UserRepository;
import com.mad.lifeapp.service.DeviceTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceTokenServiceImpl implements DeviceTokenService {

    private final DeviceTokenRepository deviceTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public void saveOrUpdateToken(String token, DeviceTokenRequest deviceTokenRequest) throws ParserTokenException, NotFoundException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException("User not found"));
        DeviceTokenEntity deviceTokenEntity = deviceTokenRepository.findByToken(deviceTokenRequest.getToken())
                .orElse(DeviceTokenEntity.builder()
                        .token(deviceTokenRequest.getToken())
                        .build());
        deviceTokenEntity.setUser(user);
        deviceTokenRepository.save(deviceTokenEntity);
        log.info("Update user for device token userId:"+user.getId()+"device-token:"+deviceTokenEntity.getToken()+"------------------");

    }

    @Override
    public void deleteToken(String deviceToken) throws NotFoundException {
        DeviceTokenEntity deviceTokenEntity = deviceTokenRepository.findByToken(deviceToken)
                .orElseThrow(()-> new NotFoundException("Device token not found"));
        deviceTokenRepository.delete(deviceTokenEntity);
        log.info("Delete device token:"+ deviceTokenEntity.getToken());
    }
}
