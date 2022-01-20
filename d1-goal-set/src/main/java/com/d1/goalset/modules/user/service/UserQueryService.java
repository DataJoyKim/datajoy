package com.d1.goalset.modules.user.service;

import com.d1.goalset.modules.user.domain.GoalSetter;

public interface UserQueryService {
	GoalSetter findGoalSetterBy(Long userId);
}
