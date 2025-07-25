package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.component.JwtUtils;
import com.mad.lifeapp.dto.request.UserHealthProfileRequest;
import com.mad.lifeapp.dto.response.UserHealthProfileResponse;
import com.mad.lifeapp.entity.NotificationEntity;
import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.entity.UserHealthProfileEntity;
import com.mad.lifeapp.enums.UserGenderEnum;
import com.mad.lifeapp.enums.UserStatusEnum;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.exception.UserNotFoundException;
import com.mad.lifeapp.repository.INotificationRepository;
import com.mad.lifeapp.repository.UserHealthProfileRepository;
import com.mad.lifeapp.repository.UserRepository;
import com.mad.lifeapp.service.UserHealthProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class UserHealthProfileServiceImpl implements UserHealthProfileService {

    private final UserHealthProfileRepository userHealthProfileRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final INotificationRepository notificationRepository;

    @Override
    public UserHealthProfileResponse createUserHealthProfile(UserHealthProfileRequest userHealthProfileRequest, String token) throws ParserTokenException, UserNotFoundException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(LocalTime.MAX);
        UserHealthProfileEntity userHealthProfileEntity = userHealthProfileRepository
                .findLatestByUserId(user.getId(), startOfDay, endOfDay)
                .orElse(new UserHealthProfileEntity());
        userHealthProfileEntity.setAge(userHealthProfileRequest.getAge());
        userHealthProfileEntity.setGoal(userHealthProfileRequest.getGoal());
        userHealthProfileEntity.setHeight(userHealthProfileRequest.getHeight());
        userHealthProfileEntity.setWeight(userHealthProfileRequest.getWeight());
        userHealthProfileEntity.setGender(userHealthProfileRequest.getGender());
        userHealthProfileEntity.setActivityLevel(userHealthProfileRequest.getActivityLevel());
        userHealthProfileEntity.setUser(user);
        this.calculateDailyCalories(userHealthProfileEntity);
        userHealthProfileRepository.save(userHealthProfileEntity);
        user.setStatus(UserStatusEnum.ACTIVE);
        userRepository.save(user);
        this.createNotification(user);
        return UserHealthProfileResponse.builder()
                .age(userHealthProfileEntity.getAge())
                .gender(userHealthProfileEntity.getGender())
                .goal(userHealthProfileEntity.getGoal())
                .height(userHealthProfileEntity.getHeight())
                .weight(userHealthProfileEntity.getWeight())
                .activityLevel(userHealthProfileEntity.getActivityLevel())
                .dailyCaloriesGoal(userHealthProfileEntity.getDailyCaloriesGoal())
                .createdAt(userHealthProfileEntity.getCreatedAt())
                .queryDate(LocalDate.now())
                .build();
    }

    @Override
    public UserHealthProfileResponse getUserHealthProfile(String token) throws ParserTokenException {
        Long userId = jwtUtils.getUserId(token);
        UserHealthProfileEntity userHealthProfileEntity = userHealthProfileRepository.findLatestByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin sức khỏe của người dùng"));
        return UserHealthProfileResponse.builder()
                .age(userHealthProfileEntity.getAge())
                .gender(userHealthProfileEntity.getGender())
                .goal(userHealthProfileEntity.getGoal())
                .height(userHealthProfileEntity.getHeight())
                .weight(userHealthProfileEntity.getWeight())
                .activityLevel(userHealthProfileEntity.getActivityLevel())
                .dailyCaloriesGoal(userHealthProfileEntity.getDailyCaloriesGoal())
                .createdAt(userHealthProfileEntity.getCreatedAt())
                .queryDate(LocalDate.now())
                .build();
    }

    @Override
    public UserHealthProfileResponse getUserHealthProfileByDate(String token, LocalDate date) throws ParserTokenException {
        Long userId = jwtUtils.getUserId(token);
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        UserHealthProfileEntity userHealthProfileEntity = userHealthProfileRepository.findLatestByUserIdAndDate(userId, endOfDay)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin sức khỏe của người dùng"));
        return UserHealthProfileResponse.builder()
                .age(userHealthProfileEntity.getAge())
                .gender(userHealthProfileEntity.getGender())
                .goal(userHealthProfileEntity.getGoal())
                .height(userHealthProfileEntity.getHeight())
                .weight(userHealthProfileEntity.getWeight())
                .activityLevel(userHealthProfileEntity.getActivityLevel())
                .dailyCaloriesGoal(userHealthProfileEntity.getDailyCaloriesGoal())
                .createdAt(userHealthProfileEntity.getCreatedAt())
                .queryDate(date)
                .build();
    }

    public void calculateDailyCalories(UserHealthProfileEntity profile) {
        if (profile == null) {
            throw new IllegalArgumentException("UserHealthProfileEntity cannot be null");
        }

        // 1. Tính BMR
        double bmr;
        if (profile.getGender() == UserGenderEnum.MALE) {
            bmr = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * profile.getAge() + 5;
        } else {
            bmr = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * profile.getAge() - 161;
        }

        // 2. TDEE = BMR * Activity Factor
        double activityFactor = profile.getActivityLevel() != null
                ? profile.getActivityLevel().getActivityFactor()
                : 1.2; // default SEDENTARY nếu null
        double tdee = bmr * activityFactor;

        // 3. Điều chỉnh theo Goal
        int calorieAdjustment = profile.getGoal() != null
                ? profile.getGoal().getCalorieAdjustment()
                : 0; // default maintain nếu null
        tdee += calorieAdjustment;

        // 4. Không để calories âm
        tdee = Math.max(tdee, 0);
        profile.setDailyCaloriesGoal((float) tdee);
    }

    private void createNotification(UserEntity user) {
        NotificationEntity notification = notificationRepository.findByUserId(user.getId())
                .orElse(NotificationEntity.builder()
                        .user(user)
                        .breakfastTime(LocalTime.of(7, 0))
                        .lunchTime(LocalTime.of(12, 0))
                        .dinnerTime(LocalTime.of(18, 0))
                        .build());
        notificationRepository.save(notification);
    }

}
