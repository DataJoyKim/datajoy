package com.d1.goalset.modules.goal.service;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.GoalSetter;

public interface PersonGoalService {
	Long write(GoalSetter goalSetter, GoalWritingRequest params);
	
	void update(Long goalId, GoalSetter goalSetter, GoalWritingRequest params);
	
	void delete(Long goalId, GoalSetter goalSetter);
	
	void submit(GoalSetter goalSetter);
	
	void cancel(GoalSetter goalSetter);
}
