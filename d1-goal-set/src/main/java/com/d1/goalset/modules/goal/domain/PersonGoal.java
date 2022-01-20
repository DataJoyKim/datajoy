package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.user.domain.GoalSetter;

public class PersonGoal implements GoalType {

	@Override
	public GoalTypeCode getGoalTypeCode() {
		return GoalTypeCode.PERSON_GOAL;
	}

	@Override
	public Long getTargetId(GoalSetter goalSetter) {
		return goalSetter.getId();
	}
	 
}
