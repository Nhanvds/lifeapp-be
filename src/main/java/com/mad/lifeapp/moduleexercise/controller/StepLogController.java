package com.mad.lifeapp.moduleexercise.controller;


import com.mad.lifeapp.moduleexercise.entity.StepLog;
import com.mad.lifeapp.moduleexercise.service.StepLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/step-logs")
@RequiredArgsConstructor
public class StepLogController {
    private final StepLogService service;

    @GetMapping
    public List<StepLog> getAll(@AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("user_id");
        return service.findAllByUser(userId);
    }

    @GetMapping("/{id}")
    public StepLog getById(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("user_id");
        return service.findByIdForUser(id, userId);
    }

    @PostMapping
    public ResponseEntity<Void> createAll(
            @RequestBody List<StepLog> logs,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = jwt.getClaim("user_id");
        service.createAllForUser(logs, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public StepLog update(
            @PathVariable Integer id,
            @RequestBody StepLog log,
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






