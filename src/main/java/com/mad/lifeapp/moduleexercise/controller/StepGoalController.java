package com.mad.lifeapp.moduleexercise.controller;


import com.mad.lifeapp.moduleexercise.service.StepGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/step-goals")
public class StepGoalController {
    private final StepGoalService service;



    // GET → 200 nếu có, 404 nếu chưa set
    @GetMapping("/{userId}")
    public ResponseEntity<Integer> getGoal(@PathVariable long userId) {
        return service.getGoal(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT luôn upsert, trả về 204 No Content
    @PutMapping("/{userId}")
    public ResponseEntity<Void> setGoal(
            @PathVariable long userId,
            @RequestParam("goal") int goal
    ) {
        service.upsert(userId, goal);
        return ResponseEntity.noContent().build();
    }


}

