package com.mad.lifeapp.moduleexercise.controller;



import com.mad.lifeapp.moduleexercise.entity.WorkoutSet;
import com.mad.lifeapp.moduleexercise.service.WorkoutSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sets")
@RequiredArgsConstructor
public class WorkoutSetController {
    private final WorkoutSetService service;

    // List all sets belonging to authenticated user
    @GetMapping
    public List<WorkoutSet> getAll(@AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("user_id");
        return service.findAllByUser(userId);
    }

    // Get single set by id (and user)
    @GetMapping("/{id}")
    public WorkoutSet getById(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("user_id");
        return service.findByIdForUser(id, userId);
    }

    // Create new set for authenticated user
    @PostMapping
    public ResponseEntity<WorkoutSet> create(
            @RequestBody WorkoutSet set,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = jwt.getClaim("user_id");
        WorkoutSet saved = service.createForUser(set, userId);
        return ResponseEntity
                .created(URI.create("/api/sets/" + saved.getId()))
                .body(saved);
    }

    // Update existing set (only if it belongs to user)
    @PutMapping("/{id}")
    public WorkoutSet update(
            @PathVariable Integer id,
            @RequestBody WorkoutSet set,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = jwt.getClaim("user_id");
        return service.updateForUser(id, set, userId);
    }

    // Delete set
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



