package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.ProgramExerciseOrder;
import com.mad.lifeapp.moduleexercise.entity.ProgramExerciseOrderId;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.ProgramExerciseOrderRepository;
import com.mad.lifeapp.moduleexercise.service.ProgramExerciseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramExerciseOrderServiceImpl implements ProgramExerciseOrderService {
    private final ProgramExerciseOrderRepository repo;

    @Override
    public List<ProgramExerciseOrder> findAll() {
        return repo.findAll();
    }

    @Override
    public ProgramExerciseOrder findById(Integer pid, Integer eid) {
        return repo.findById(new ProgramExerciseOrderId(pid, eid))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found " + pid + "," + eid));
    }

    @Override
    public ProgramExerciseOrder create(ProgramExerciseOrder o) {
        return repo.save(o);
    }

    @Override
    public ProgramExerciseOrder update(Integer pid, Integer eid, ProgramExerciseOrder o) {
        ProgramExerciseOrder ex = findById(pid, eid);
        ex.setOrder(o.getOrder());
        return repo.save(ex);
    }

    @Override
    public void delete(Integer pid, Integer eid) {
        ProgramExerciseOrder ex = findById(pid, eid);
        repo.delete(ex);
    }
}
