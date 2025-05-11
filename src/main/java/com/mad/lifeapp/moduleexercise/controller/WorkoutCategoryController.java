package com.mad.lifeapp.moduleexercise.controller;


import com.mad.lifeapp.moduleexercise.entity.WorkoutCategory;
import com.mad.lifeapp.moduleexercise.service.WorkoutCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class WorkoutCategoryController {
    private final WorkoutCategoryService service;

    @GetMapping
    public List<WorkoutCategory> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public WorkoutCategory getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<WorkoutCategory> create(@RequestBody WorkoutCategory c) {
        WorkoutCategory saved = service.create(c);
        return ResponseEntity
                .created(URI.create("/api/categories/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public WorkoutCategory update(
            @PathVariable Integer id,
            @RequestBody WorkoutCategory c
    ) {
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
