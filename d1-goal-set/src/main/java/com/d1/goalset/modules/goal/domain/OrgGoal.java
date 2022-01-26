package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.user.domain.User;

public class OrgGoal implements GoalType {

	@Override 
	public GoalTypeCode getGoalTypeCode() {
		return GoalTypeCode.ORG_GOAL;
	}

	@Override
	public Long getTargetId(User goalSetter) {
		return goalSetter.getOrg().getId();
	}

}
