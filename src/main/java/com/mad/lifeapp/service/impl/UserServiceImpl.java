package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.component.JwtUtils;
import com.mad.lifeapp.dto.request.UserRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.dto.response.UserResponse;
import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.entity.VerificationCodeEntity;
import com.mad.lifeapp.enums.UserRoleEnum;
import com.mad.lifeapp.enums.UserStatusEnum;
import com.mad.lifeapp.exception.InvalidCredentialsException;
import com.mad.lifeapp.exception.InvalidException;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.mapper.UserMapper;
import com.mad.lifeapp.repository.UserRepository;
import com.mad.lifeapp.repository.VerificationCodeRepository;
import com.mad.lifeapp.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    @Override
    public UserResponse register(UserRequest userRequest) throws NotFoundException, InvalidException {
        VerificationCodeEntity verificationCodeEntity = verificationCodeRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(()->new InvalidException("Email invalid"));
        if(!verificationCodeEntity.getCode().equals(userRequest.getVerificationCode())){
            throw new InvalidException("Verification code invalid");
        }
        Optional<UserEntity> userExisted = userRepository.findByEmail(userRequest.getEmail());
        if(userExisted.isPresent()) throw new InvalidException("Email existed");
        UserEntity user = UserEntity.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(UserRoleEnum.USER)
                .status(UserStatusEnum.INACTIVE)
                .build();
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public TokenResponse login(UserRequest userRequest) throws NotFoundException, InvalidCredentialsException, JOSEException {
        UserEntity user = userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(()-> new NotFoundException("User not found"));
        if(!passwordEncoder.matches(userRequest.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("Password invalid");
        }
        return TokenResponse.builder()
                .token(jwtUtils.generateToken(user))
                .build();
    }

}
