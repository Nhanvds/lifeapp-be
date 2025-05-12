package com.mad.lifeapp.moduleexercise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "step_goals")
public class StepGoal {
    @Id
    private Long userId;
    private Integer goal;

    protected StepGoal() {}
    public StepGoal(Long userId, Integer goal) {
        this.userId = userId;
        this.goal   = goal;
    }

    public Long getUserId() { return userId; }
    public Integer getGoal()   { return goal;   }
    public void setGoal(Integer goal) { this.goal = goal; }
}
