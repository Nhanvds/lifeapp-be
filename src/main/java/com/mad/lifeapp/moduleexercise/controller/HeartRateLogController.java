package com.mad.lifeapp.moduleexercise.controller;


import com.mad.lifeapp.moduleexercise.entity.HeartRateLog;
import com.mad.lifeapp.moduleexercise.service.HeartRateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/heart-rate-logs")
@RequiredArgsConstructor
public class HeartRateLogController {
    private final HeartRateLogService service;

    @GetMapping
    public List<HeartRateLog> getAll(@AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("user_id");
        return service.findAllByUser(userId);
    }

    @GetMapping("/{id}")
    public HeartRateLog getById(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("user_id");
        return service.findByIdForUser(id, userId);
    }

    @PostMapping
    public ResponseEntity<Void> createAll(
            @RequestBody List<HeartRateLog> logs,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = jwt.getClaim("user_id");
        service.createAllForUser(logs, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public HeartRateLog update(
            @PathVariable Integer id,
            @RequestBody HeartRateLog log,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = jwt.getClaim("user_id");
        return service.updateForUser(id, log, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = jwt.getClaim("user_id");
        service.deleteForUser(id, userId);
        return ResponseEntity.noContent().build();
    }
}
