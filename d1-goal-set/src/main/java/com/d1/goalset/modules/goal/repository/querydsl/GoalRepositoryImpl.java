package com.d1.goalset.modules.goal.repository.querydsl;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.QGoal;
import com.d1.goalset.modules.goal.domain.QGoalPlan;
import com.querydsl.jpa.JPQLQuery;

public class GoalRepositoryImpl extends QuerydslRepositorySupport implements GoalRepositoryQuerydsl {

	public GoalRepositoryImpl() {
		super(Goal.class);
	}

	@Override
	public Optional<Goal> findGoalBy(Long goalId) {
		QGoal qGoal = QGoal.goal;
		QGoalPlan qGoalPlan = QGoalPlan.goalPlan;
		
		JPQLQuery<Goal> query = from(qGoal)
				.leftJoin(qGoal.goalPlans, qGoalPlan)
				.where(qGoal.id.eq(goalId));
		
		return Optional.ofNullable(query.fetchOne());
	}

}
