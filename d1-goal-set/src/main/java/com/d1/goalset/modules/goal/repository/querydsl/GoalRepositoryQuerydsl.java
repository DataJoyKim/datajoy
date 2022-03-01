package com.d1.goalset.modules.goal.repository.querydsl;

import java.util.List;
import java.util.Optional;

import com.d1.goalset.modules.goal.domain.Goal;

public interface GoalRepositoryQuerydsl {

	Optional<Goal> findGoalBy(String seasonCd, String companyCd, Long targetId, Long goalId);

	List<Goal> findGoalBy(String seasonCd, String companyCd, Long targetId);
}
