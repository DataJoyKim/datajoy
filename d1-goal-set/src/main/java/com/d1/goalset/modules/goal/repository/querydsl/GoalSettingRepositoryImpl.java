package com.d1.goalset.modules.goal.repository.querydsl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.d1.goalset.modules.goal.domain.GoalSetting;

public class GoalSettingRepositoryImpl extends QuerydslRepositorySupport implements GoalSettingRepositoryQuerydsl {

	public GoalSettingRepositoryImpl() {
		super(GoalSetting.class);
	}
}
