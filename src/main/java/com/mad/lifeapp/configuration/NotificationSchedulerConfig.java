package com.mad.lifeapp.configuration;

import com.mad.lifeapp.component.FcmSender;
import com.mad.lifeapp.entity.NotificationEntity;
import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.repository.DeviceTokenRepository;
import com.mad.lifeapp.repository.INotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class NotificationSchedulerConfig {

    private final INotificationRepository notificationRepository;
    private final DeviceTokenRepository deviceTokenRepository;

    // Giả định bạn có 1 lớp gửi FCM
    private final FcmSender fcmSender;

    // Chạy mỗi 5 phút
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void sendScheduledNotifications() {
        LocalTime now = LocalTime.now();
        LocalTime in5Minutes = now.plusMinutes(5);

        List<NotificationEntity> notifications = notificationRepository.findAll();

        for (NotificationEntity notification : notifications) {
            UserEntity user = notification.getUser();

            checkAndSend(notification.getBreakfastTime(), "Đến giờ ăn sáng rồi!", user, now, in5Minutes);
            checkAndSend(notification.getLunchTime(), "Đến giờ ăn trưa rồi!", user, now, in5Minutes);
            checkAndSend(notification.getDinnerTime(), "Đến giờ ăn tối rồi!", user, now, in5Minutes);
        }
    }

    private void checkAndSend(LocalTime time, String message, UserEntity user, LocalTime now, LocalTime in5Minutes) {
        if (time != null &&
                !time.isBefore(now) &&
                !time.isAfter(in5Minutes)) {

            deviceTokenRepository.findByUser(user).ifPresent(tokenEntity ->
                    fcmSender.sendNotification(tokenEntity.getToken(), "Nhắc nhở", message)
            );
        }
    }
}

