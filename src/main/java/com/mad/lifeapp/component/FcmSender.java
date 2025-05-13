package com.mad.lifeapp.component;

import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FcmSender {

    public void sendNotification(String token, String title, String body) {
        try {
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();
            AndroidConfig androidConfig = AndroidConfig.builder()
                    .setPriority(AndroidConfig.Priority.HIGH) // Đảm bảo thông báo có priority cao
                    .build();

            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(notification)
                    .setAndroidConfig(androidConfig)  // Áp dụng AndroidConfig
                    .build();

            // Gửi thông điệp FCM
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("FCM gửi thành công: {}", response);


        } catch (FirebaseMessagingException e) {
            log.error("FCM gửi thất bại: {}", e.getMessage(), e);
        }
    }
}

