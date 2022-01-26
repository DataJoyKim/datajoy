package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.user.domain.User;

public interface GoalType {

	GoalTypeCode getGoalTypeCode();

	Long getTargetId(User goalSetter);

}
