package com.mad.lifeapp.moduleexercise.controller;


import com.mad.lifeapp.moduleexercise.entity.ProgramExerciseOrder;
import com.mad.lifeapp.moduleexercise.service.ProgramExerciseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class ProgramExerciseOrderController {
    private final ProgramExerciseOrderService service;

    @GetMapping
    public List<ProgramExerciseOrder> getAll() {
        return service.findAll();
    }

    @GetMapping("/{pid},{eid}")
    public ProgramExerciseOrder getById(@PathVariable Integer pid, @PathVariable Integer eid) {
        return service.findById(pid, eid);
    }

    @PostMapping
    public ResponseEntity<ProgramExerciseOrder> create(@RequestBody ProgramExerciseOrder o) {
        ProgramExerciseOrder s = service.create(o);
        return ResponseEntity.created(URI.create("/api/orders/" + s.getId().getProgramId() + "," + s.getId().getExerciseId())).body(s);
    }

    @PutMapping("/{pid},{eid}")
    public ProgramExerciseOrder update(@PathVariable Integer pid, @PathVariable Integer eid, @RequestBody ProgramExerciseOrder o) {
        return service.update(pid, eid, o);
    }

    @DeleteMapping("/{pid},{eid}")
    public ResponseEntity<Void> delete(@PathVariable Integer pid, @PathVariable Integer eid) {
        service.delete(pid, eid);
        return ResponseEntity.noContent().build();
    }
}
