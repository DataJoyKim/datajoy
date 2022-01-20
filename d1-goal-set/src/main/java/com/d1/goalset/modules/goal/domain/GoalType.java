package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.user.domain.GoalSetter;

public interface GoalType {

	GoalTypeCode getGoalTypeCode();

	Long getTargetId(GoalSetter goalSetter);

}
