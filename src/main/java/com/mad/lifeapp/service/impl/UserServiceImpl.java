package com.mad.lifeapp.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.mad.lifeapp.component.JwtUtils;
import com.mad.lifeapp.dto.request.FirebaseTokenRequest;
import com.mad.lifeapp.dto.request.PasswordRequest;
import com.mad.lifeapp.dto.request.UserRequest;
import com.mad.lifeapp.dto.response.TokenResponse;
import com.mad.lifeapp.dto.response.UserProfileResponse;
import com.mad.lifeapp.dto.response.UserResponse;
import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.entity.UserHealthProfileEntity;
import com.mad.lifeapp.entity.VerificationCodeEntity;
import com.mad.lifeapp.enums.UserRoleEnum;
import com.mad.lifeapp.enums.UserStatusEnum;
import com.mad.lifeapp.exception.*;
import com.mad.lifeapp.mapper.UserMapper;
import com.mad.lifeapp.repository.UserHealthProfileRepository;
import com.mad.lifeapp.repository.UserRepository;
import com.mad.lifeapp.repository.VerificationCodeRepository;
import com.mad.lifeapp.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final UserHealthProfileRepository userHealthProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final FirebaseAuth firebaseAuth;

    @Override
    public UserResponse register(UserRequest userRequest) throws InvalidException {
        log.info(userRequest.getEmail());
        VerificationCodeEntity verificationCodeEntity = verificationCodeRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new InvalidException("Email invalid"));
        if (!verificationCodeEntity.getCode().equals(userRequest.getVerificationCode())
                || verificationCodeEntity.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new InvalidException("Verification code invalid");
        }
        Optional<UserEntity> userExisted = userRepository.findByEmail(userRequest.getEmail());
        if (userExisted.isPresent()) throw new InvalidException("Email existed");
        UserEntity user = UserEntity.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(UserRoleEnum.USER)
                .status(UserStatusEnum.INACTIVE)
                .build();
        userRepository.save(user);
        verificationCodeRepository.delete(verificationCodeEntity);
        return userMapper.toResponse(user);
    }

    @Override
    public TokenResponse login(UserRequest userRequest) throws NotFoundException, InvalidCredentialsException, JOSEException {
        UserEntity user = userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Password invalid");
        }
        return TokenResponse.builder()
                .token(jwtUtils.generateToken(user))
                .build();
    }

    @Override
    public UserResponse updateUserInfo(String token, UserRequest userRequest) throws ParserTokenException, UserNotFoundException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setCountry(userRequest.getCountry());
        user.setFullname(userRequest.getUsername());
        user.setImageUrl(userRequest.getImageUrl());
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public boolean changePassword(PasswordRequest passwordRequest, String token) throws ParserTokenException, UserNotFoundException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        // user.getPassword null khi đăng nhập với tài khoản google và chưa cài mật khẩu
        if (user.getPassword() == null || passwordEncoder.matches(passwordRequest.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean resetPassword(String token, PasswordRequest passwordRequest) throws ParserTokenException, UserNotFoundException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserResponse getUser(String token) throws ParserTokenException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserProfileResponse getUserProfile(String token) throws ParserTokenException, UserNotFoundException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        UserHealthProfileEntity userHealthProfileEntity = userHealthProfileRepository.findLatestByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin sức khỏe của người dùng"));
        return UserProfileResponse.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .imageUrl(user.getImageUrl())
                .weight(userHealthProfileEntity.getWeight())
                .height(userHealthProfileEntity.getHeight())
                .age(userHealthProfileEntity.getAge())
                .build();
    }

    @Override
    public TokenResponse loginWithFirebase(FirebaseTokenRequest firebaseTokenRequest) throws FirebaseAuthException, JOSEException {
        FirebaseToken decodedToken = firebaseAuth.verifyIdToken(firebaseTokenRequest.getIdToken());
        String email = decodedToken.getEmail();
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElse(UserEntity.builder()
                        .email(email)
                        .role(UserRoleEnum.USER)
                        .status(UserStatusEnum.INACTIVE)
                        .build());
        userRepository.save(userEntity);
        String accessToken = jwtUtils.generateToken(userEntity);
        return TokenResponse.builder()
                .token(accessToken)
                .build();
    }
}
