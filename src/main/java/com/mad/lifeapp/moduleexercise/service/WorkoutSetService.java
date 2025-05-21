package com.mad.lifeapp.moduleexercise.service;




import com.mad.lifeapp.moduleexercise.dto.WorkoutSetUpdateDto;
import com.mad.lifeapp.moduleexercise.entity.WorkoutSet;

import java.util.List;

public interface WorkoutSetService {
    List<WorkoutSet> findAllByUser(Long userId);
    WorkoutSet findByIdForUser(Integer id, Long userId);
    WorkoutSet createForUser(WorkoutSet set, Long userId);
    WorkoutSet updateForUser(Integer id, WorkoutSetUpdateDto dto, Long userId);
    void deleteForUser(Integer id, Long userId);
}
