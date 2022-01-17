package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.code.GoalTypeCode;

public class OrgGoal implements GoalType {

	@Override
	public GoalTypeCode getGoalTypeCode() {
		return GoalTypeCode.ORG_GOAL;
	}

}
