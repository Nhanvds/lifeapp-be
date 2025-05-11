package com.mad.lifeapp.moduleexercise.controller;


import com.mad.lifeapp.moduleexercise.entity.Exercise;
import com.mad.lifeapp.moduleexercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService service;

    @GetMapping
    public List<Exercise> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Exercise getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody Exercise e) {
        Exercise saved = service.create(e);
        return ResponseEntity.created(URI.create("/api/exercises/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Exercise update(@PathVariable Integer id, @RequestBody Exercise e) {
        return service.update(id, e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



