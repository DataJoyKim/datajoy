package com.d1.goalset.modules.goal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.goal.domain.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long>{
	
}
