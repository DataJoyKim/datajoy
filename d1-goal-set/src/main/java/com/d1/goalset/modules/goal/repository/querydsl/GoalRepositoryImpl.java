package com.d1.goalset.modules.goal.repository.querydsl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.QGoal;
import com.d1.goalset.modules.goal.domain.QGoalPlan;
import com.querydsl.jpa.JPQLQuery;

public class GoalRepositoryImpl extends QuerydslRepositorySupport implements GoalRepositoryQuerydsl {

	public GoalRepositoryImpl() {
		super(Goal.class);
	}

	@Override
	public Optional<Goal> findGoalBy(Long targetId, Long goalId) {
		QGoal qGoal = QGoal.goal;
		QGoalPlan qGoalPlan = QGoalPlan.goalPlan;
		
		JPQLQuery<Goal> query = from(qGoal)
				.leftJoin(qGoal.goalPlans, qGoalPlan)
				.where(qGoal.id.eq(goalId)
						.and(qGoal.targetId.eq(targetId))
						.and(qGoal.goalWritingStateCd.ne(GoalWritingState.DELETE))
						)
				.distinct();
		
		return Optional.ofNullable(query.fetchOne());
	}

	@Override
	public List<Goal> findGoalBy(Long targetId) {
		QGoal qGoal = QGoal.goal;
		QGoalPlan qGoalPlan = QGoalPlan.goalPlan;
		
		JPQLQuery<Goal> query = from(qGoal)
				.leftJoin(qGoal.goalPlans, qGoalPlan)
				.where(qGoal.targetId.eq(targetId)
						.and(qGoal.goalWritingStateCd.ne(GoalWritingState.DELETE))
						)
				.distinct();
		
		return query.fetch();
	}

}
