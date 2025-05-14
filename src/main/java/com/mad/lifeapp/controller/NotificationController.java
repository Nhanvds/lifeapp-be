package com.mad.lifeapp.controller;

import com.mad.lifeapp.dto.request.NotificationRequest;
import com.mad.lifeapp.dto.response.NotificationResponse;
import com.mad.lifeapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<NotificationResponse> getNotification(@PathVariable Long userId) {
        NotificationResponse response = notificationService.getNotificationByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<NotificationResponse> updateNotification(
            @PathVariable Long userId,
            @RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.updateNotification(userId, request);
        return ResponseEntity.ok(response);
    }
}