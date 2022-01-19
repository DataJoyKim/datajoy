package com.d1.goalset.modules.goal.service.query;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalSettingResponse;
import com.d1.goalset.modules.user.domain.GoalSetter;

public interface PersonGoalQueryService {
	GoalSettingResponse getPersonGoalSetting(GoalSetter goalSetter);
	
	GoalResponse getGoal(Long goalId);
}
