package com.mad.lifeapp.moduleexercise.controller;


import com.mad.lifeapp.moduleexercise.dto.WorkoutProgramUpdateDto;
import com.mad.lifeapp.moduleexercise.entity.WorkoutProgram;
import com.mad.lifeapp.moduleexercise.service.WorkoutProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class WorkoutProgramController {
    private final WorkoutProgramService service;

    @GetMapping
    public List<WorkoutProgram> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public WorkoutProgram getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<WorkoutProgram> create(@RequestBody WorkoutProgram p) {
        WorkoutProgram saved = service.create(p);
        return ResponseEntity.created(URI.create("/api/programs/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public WorkoutProgram update(@PathVariable Integer id, @RequestBody WorkoutProgram p) {
        return service.update(id, p);
    }

    @PatchMapping("/{id}")
    public WorkoutProgram patch(
            @PathVariable Integer id,
            @RequestBody WorkoutProgramUpdateDto dto
    ) {
        return service.partialUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
