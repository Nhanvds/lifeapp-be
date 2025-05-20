package com.mad.lifeapp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AchievementResponse {
    private Long id;
    private String description;
    private String imageUrl;
}
