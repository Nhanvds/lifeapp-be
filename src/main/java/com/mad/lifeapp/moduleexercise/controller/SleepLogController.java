package com.mad.lifeapp.moduleexercise.controller;



import com.mad.lifeapp.moduleexercise.entity.SleepLog;
import com.mad.lifeapp.moduleexercise.service.SleepLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sleep-logs")
@RequiredArgsConstructor
public class SleepLogController {
    private final SleepLogService service;

    @GetMapping
    public List<SleepLog> getAll(@AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("user_id");
        return service.findAllByUser(userId);
    }

    @GetMapping("/{id}")
    public SleepLog getById(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("user_id");
        return service.findByIdForUser(id, userId);
    }

    @PostMapping
    public ResponseEntity<Void> createAll(
            @RequestBody List<SleepLog> logs,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = jwt.getClaim("user_id");
        service.createAllForUser(logs, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public SleepLog update(
            @PathVariable Integer id,
            @RequestBody SleepLog log,
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


