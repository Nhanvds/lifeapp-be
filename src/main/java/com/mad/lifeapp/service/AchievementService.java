package com.mad.lifeapp.service;

import com.mad.lifeapp.dto.request.AchievementRequest;
import com.mad.lifeapp.dto.response.AchievementResponse;
import com.mad.lifeapp.exception.ParserTokenException;

public interface AchievementService {

    AchievementResponse createAchievement(String token, AchievementRequest achievementRequest) throws ParserTokenException;
}
