package com.mad.lifeapp.moduleexercise.service.impl;


import com.mad.lifeapp.moduleexercise.entity.ExerciseStatus;
import com.mad.lifeapp.moduleexercise.entity.ExerciseStatusId;
import com.mad.lifeapp.moduleexercise.exception.ResourceNotFoundException;
import com.mad.lifeapp.moduleexercise.repository.ExerciseStatusRepository;
import com.mad.lifeapp.moduleexercise.service.ExerciseStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseStatusServiceImpl implements ExerciseStatusService {
    private final ExerciseStatusRepository repo;

    @Override
    public List<ExerciseStatus> findAll() {
        return repo.findAll();
    }

    @Override
    public ExerciseStatus findById(Integer wsid, Integer eid) {
        return repo.findById(new ExerciseStatusId(wsid, eid))
                .orElseThrow(() -> new ResourceNotFoundException("Status not found " + wsid + "," + eid));
    }

    @Override
    public ExerciseStatus create(ExerciseStatus s) {
        return repo.save(s);
    }

    @Override
    public ExerciseStatus update(Integer wsid, Integer eid, ExerciseStatus s) {
        ExerciseStatus es = findById(wsid, eid);
        es.setStatus(s.getStatus());
        es.setActualDurationSeconds(s.getActualDurationSeconds());
        return repo.save(es);
    }

    @Override
    public void delete(Integer wsid, Integer eid) {
        ExerciseStatus es = findById(wsid, eid);
        repo.delete(es);
    }
}