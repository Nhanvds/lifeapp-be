package com.mad.lifeapp.moduleexercise.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseStatusId implements Serializable {
    private Integer workoutSetId;
    private Integer exerciseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseStatusId that)) return false;
        return Objects.equals(workoutSetId, that.workoutSetId)
                && Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workoutSetId, exerciseId);
    }
}
