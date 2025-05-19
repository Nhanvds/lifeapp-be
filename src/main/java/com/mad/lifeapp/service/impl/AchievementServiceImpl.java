package com.mad.lifeapp.service.impl;

import com.mad.lifeapp.component.JwtUtils;
import com.mad.lifeapp.dto.request.AchievementRequest;
import com.mad.lifeapp.dto.response.AchievementResponse;
import com.mad.lifeapp.entity.AchievementEntity;
import com.mad.lifeapp.entity.UserEntity;
import com.mad.lifeapp.exception.NotFoundException;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.repository.AchievementRepository;
import com.mad.lifeapp.repository.UserRepository;
import com.mad.lifeapp.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public AchievementResponse createAchievement(String token, AchievementRequest achievementRequest) throws ParserTokenException {
        Long userId = jwtUtils.getUserId(token);
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException("User not found"));
        AchievementEntity achievementEntity = AchievementEntity.builder()
                .user(userEntity)
                .title(achievementRequest.getTitle())
                .description(achievementRequest.getDescription())
                .imageUrl(achievementRequest.getImageUrl())
                .build();
        achievementRepository.save(achievementEntity);
        return AchievementResponse.builder()
                .id(achievementEntity.getId())
                .title(achievementEntity.getTitle())
                .imageUrl(achievementEntity.getImageUrl())
                .description(achievementEntity.getDescription())
                .build();
    }
}
