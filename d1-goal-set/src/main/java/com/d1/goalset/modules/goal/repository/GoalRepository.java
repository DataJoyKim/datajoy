package com.d1.goalset.modules.goal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.repository.querydsl.GoalRepositoryQuerydsl;

public interface GoalRepository extends JpaRepository<Goal, Long>, GoalRepositoryQuerydsl {

	Optional<Goal> findByGoalSettingIdAndId(Long id, Long goalId);
	
}
