package com.mad.lifeapp.moduleexercise.controller;


import com.mad.lifeapp.moduleexercise.entity.ExerciseStatus;
import com.mad.lifeapp.moduleexercise.service.ExerciseStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/statuses")
@RequiredArgsConstructor
public class ExerciseStatusController {
    private final ExerciseStatusService service;

    @GetMapping
    public List<ExerciseStatus> getAll() {
        return service.findAll();
    }

    @GetMapping("/{wsid},{eid}")
    public ExerciseStatus getById(@PathVariable Integer wsid, @PathVariable Integer eid) {
        return service.findById(wsid, eid);
    }

    @PostMapping
    public ResponseEntity<ExerciseStatus> create(@RequestBody ExerciseStatus s) {
        ExerciseStatus r = service.create(s);
        return ResponseEntity.created(URI.create("/api/statuses/" + r.getId().getWorkoutSetId() + "," + r.getId().getExerciseId())).body(r);
    }

    @PutMapping("/{wsid},{eid}")
    public ExerciseStatus update(@PathVariable Integer wsid, @PathVariable Integer eid, @RequestBody ExerciseStatus s) {
        return service.update(wsid, eid, s);
    }

    @DeleteMapping("/{wsid},{eid}")
    public ResponseEntity<Void> delete(@PathVariable Integer wsid, @PathVariable Integer eid) {
        service.delete(wsid, eid);
        return ResponseEntity.noContent().build();
    }
}
