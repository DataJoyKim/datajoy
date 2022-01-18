package com.d1.goalset.modules.goal.repository.querydsl;

import java.util.Optional;

import com.d1.goalset.modules.goal.domain.Goal;

public interface GoalRepositoryQuerydsl {

	Optional<Goal> findGoalBy(Long goalId);
}
