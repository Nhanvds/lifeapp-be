package com.mad.lifeapp.controller;

import com.mad.lifeapp.dto.request.AchievementRequest;
import com.mad.lifeapp.dto.response.AchievementResponse;
import com.mad.lifeapp.exception.ParserTokenException;
import com.mad.lifeapp.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/achievements")
public class AchievementController {
    private final AchievementService achievementService;

    @PostMapping("/create")
    public ResponseEntity<AchievementResponse> createAchievement(
            @RequestHeader("Authorization") String token,
            @RequestBody AchievementRequest achievementRequest
            ) throws ParserTokenException {
        return ResponseEntity.ok().body(achievementService.createAchievement(token,achievementRequest));
    }

    @GetMapping("/{achievement_id}")
    public String getAchievement(@PathVariable("achievement_id") Long id){
        return achievementService.getAchievement(id);
    }
}
