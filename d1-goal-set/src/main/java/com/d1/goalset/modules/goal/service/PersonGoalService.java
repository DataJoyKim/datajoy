package com.d1.goalset.modules.goal.service;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.User;

public interface PersonGoalService {
	Long write(User goalSetter, GoalWritingRequest params);
	
	void update(Long goalId, User goalSetter, GoalWritingRequest params);
	
	void delete(Long goalId, User goalSetter);
	
	void submit(User goalSetter);
	
	void cancel(User goalSetter);
}
