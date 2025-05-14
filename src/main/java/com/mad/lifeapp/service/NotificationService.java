package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.NotificationRequest;
import com.mad.lifeapp.dto.response.NotificationResponse;
import com.mad.lifeapp.entity.NotificationEntity;
import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Transactional
    public NotificationResponse updateNotification(Long userId, NotificationRequest request) {
        // Chuyển đổi chuỗi thời gian sang LocalTime
        LocalTime breakfastTime = request.getBreakfastTime() != null
                ? LocalTime.parse(request.getBreakfastTime(), TIME_FORMATTER)
                : null;
        LocalTime lunchTime = request.getLunchTime() != null
                ? LocalTime.parse(request.getLunchTime(), TIME_FORMATTER)
                : null;
        LocalTime dinnerTime = request.getDinnerTime() != null
                ? LocalTime.parse(request.getDinnerTime(), TIME_FORMATTER)
                : null;

        // Tìm NotificationEntity theo userId
        Optional<NotificationEntity> optionalNotification = notificationRepository.findByUserId(userId);
        NotificationEntity notification;

        if (optionalNotification.isPresent()) {
            // Nếu tồn tại, cập nhật thời gian
            notification = optionalNotification.get();
            notification.setBreakfastTime(breakfastTime);
            notification.setLunchTime(lunchTime);
            notification.setDinnerTime(dinnerTime);
        } else {
            // Nếu không tồn tại, tạo mới
            UserEntity user = new UserEntity();
            user.setId(userId); // Thiết lập id trực tiếp
            notification = NotificationEntity.builder()
                    .breakfastTime(breakfastTime)
                    .lunchTime(lunchTime)
                    .dinnerTime(dinnerTime)
                    .user(user)
                    .build();
        }

        // Lưu vào cơ sở dữ liệu
        NotificationEntity savedNotification = notificationRepository.save(notification);

        // Chuyển đổi sang NotificationResponse
        NotificationResponse response = new NotificationResponse();
        response.setId(savedNotification.getId());
        response.setUserId(savedNotification.getUser().getId());
        response.setBreakfastTime(savedNotification.getBreakfastTime());
        response.setLunchTime(savedNotification.getLunchTime());
        response.setDinnerTime(savedNotification.getDinnerTime());
        response.setCreatedAt(savedNotification.getCreatedAt());
        response.setUpdatedAt(savedNotification.getUpdatedAt());

        return response;
    }

    public NotificationResponse getNotificationByUserId(Long userId) {
        // Tìm NotificationEntity theo userId
        NotificationEntity notification = notificationRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Notification not found for userId: " + userId));

        // Chuyển đổi sang NotificationResponse
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setUserId(notification.getUser().getId());
        response.setBreakfastTime(notification.getBreakfastTime());
        response.setLunchTime(notification.getLunchTime());
        response.setDinnerTime(notification.getDinnerTime());
        response.setCreatedAt(notification.getCreatedAt());
        response.setUpdatedAt(notification.getUpdatedAt());

        return response;
    }
}