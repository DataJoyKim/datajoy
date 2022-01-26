package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalSettingResponse;
import com.d1.goalset.modules.user.domain.User;

public interface PersonGoalQueryService {
	
	GoalResponse findGoalBy(Long userId, Long goalId);

	List<GoalResponse> findGoalBy(Long userId);

	GoalSettingResponse findGoalSettingBy(User goalSetter);
}
