package com.d1.goalset.modules.goal.repository.querydsl;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.goal.domain.QGoal;
import com.d1.goalset.modules.goal.domain.QGoalPlan;
import com.d1.goalset.modules.goal.domain.QPersonGoalSetting;
import com.d1.goalset.modules.user.domain.GoalSetter;
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

	@Override
	public Optional<PersonGoalSetting> findPersonGoalSettingBy(GoalSetter goalSetter) {
		QPersonGoalSetting qPersonGoalSetting = QPersonGoalSetting.personGoalSetting;
		QGoal qGoal = QGoal.goal;
		QGoalPlan qGoalPlan = QGoalPlan.goalPlan;
		
		JPQLQuery<PersonGoalSetting> query = from(qPersonGoalSetting)
				.leftJoin(qPersonGoalSetting.goals, qGoal)
				.leftJoin(qGoal.goalPlans, qGoalPlan)
				.where(qPersonGoalSetting.goalSetter().eq(goalSetter));
		
		return Optional.ofNullable(query.fetchOne());
	}

}
