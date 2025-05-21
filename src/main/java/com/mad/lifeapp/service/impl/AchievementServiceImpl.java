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
                .orElseThrow(() -> new NotFoundException("User not found"));
        AchievementEntity achievementEntity = AchievementEntity.builder()
                .user(userEntity)
                .description(achievementRequest.getDescription())
                .imageUrl(achievementRequest.getImageUrl())
                .build();
        achievementRepository.save(achievementEntity);
        return AchievementResponse.builder()
                .id(achievementEntity.getId())
                .imageUrl(achievementEntity.getImageUrl())
                .description(achievementEntity.getDescription())
                .build();
    }

    @Override
    public String getAchievement(Long id) {
        AchievementEntity achievementEntity = achievementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Achievement not found"));

        UserEntity user = achievementEntity.getUser();
        String username = user.getFullname();
        String userImageUrl = user.getImageUrl(); // avatar
        String achievementImage = achievementEntity.getImageUrl();
        String description = achievementEntity.getDescription();

        String html = """
                <!DOCTYPE html>
                <html lang="vi">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Thành tựu của %s</title>
                    <meta name="description" content="%s">
                    <meta property="og:title" content="Thành tựu của %s">
                    <meta property="og:description" content="%s">
                    <meta property="og:image" content="%s">
                    <meta property="og:type" content="article">
                    <meta name="robots" content="index, follow">
                    <style>
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            margin: 0;
                            padding: 0;
                            background-color: #fafafa;
                            color: #222;
                        }
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 24px 16px;
                            background-color: #fff;
                            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
                            text-align: center;
                        }
                        .user-info {
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                            margin-bottom: 24px;
                        }
                        .user-info img {
                            width: 72px;
                            height: 72px;
                            border-radius: 50%%;
                            margin-bottom: 8px;
                            object-fit: cover;
                        }
                        .user-info h2 {
                            margin: 0;
                            font-size: 20px;
                            color: #2c3e50;
                        }
                        .achievement-image {
                            width: 100%%;
                            height: auto;
                            border-radius: 10px;
                            margin: 24px 0;
                            display: block;
                        }
                        .description {
                            font-size: 16px;
                            line-height: 1.6;
                            color: #555;
                            text-align: left;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="user-info">
                            %s
                            <h2>%s</h2>
                        </div>
                        <img src="%s" alt="Ảnh thành tựu" class="achievement-image"/>
                        <div class="description">%s</div>
                    </div>
                </body>
                </html>
                """.formatted(
                username,
                description,
                username,
                description,
                achievementImage,
                (userImageUrl != null && !userImageUrl.isEmpty())
                        ? "<img src=\"" + userImageUrl + "\" alt=\"Avatar của " + username + "\" />"
                        : "",
                username,
                achievementImage,
                description
        );

        return html;
    }


}
