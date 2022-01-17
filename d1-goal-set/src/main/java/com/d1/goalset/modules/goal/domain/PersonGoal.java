package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.code.GoalTypeCode;

public class PersonGoal implements GoalType {

	@Override
	public GoalTypeCode getGoalTypeCode() {
		return GoalTypeCode.PERSON_GOAL;
	}
	 
}
