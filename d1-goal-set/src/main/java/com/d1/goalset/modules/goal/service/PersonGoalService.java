package com.d1.goalset.modules.goal.service;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.User;

public interface PersonGoalService {
	Long write(User setter, GoalWritingRequest params);
	
	void update(Long goalId, User setter, GoalWritingRequest params);
	
	void delete(Long goalId, User setter);
	
	void submit(User setter);
	
	void cancel(User setter);
}
