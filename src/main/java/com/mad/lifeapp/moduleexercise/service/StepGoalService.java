package com.mad.lifeapp.moduleexercise.service;


import com.mad.lifeapp.moduleexercise.entity.StepGoal;
import com.mad.lifeapp.moduleexercise.repository.StepGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StepGoalService {
    private final StepGoalRepository repo;

    /** Trả về Optional<Integer> để controller xử lý 404 nếu empty */
    @Transactional(readOnly = true)
    public Optional<Integer> getGoal(long userId) {
        return repo.findById(userId).map(StepGoal::getGoal);
    }

    /** Upsert luôn ghi hoặc tạo mới */
    @Transactional
    public void upsert(long userId, int goal) {
        repo.findById(userId)
                .ifPresentOrElse(
                        e -> e.setGoal(goal),
                        () -> repo.save(new StepGoal(userId, goal))
                );
    }
}
