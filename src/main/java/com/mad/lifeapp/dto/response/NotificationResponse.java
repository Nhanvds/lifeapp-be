package com.mad.lifeapp.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class NotificationResponse {
    private Long id;
    private Long userId;
    private LocalTime breakfastTime;
    private LocalTime lunchTime;
    private LocalTime dinnerTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
