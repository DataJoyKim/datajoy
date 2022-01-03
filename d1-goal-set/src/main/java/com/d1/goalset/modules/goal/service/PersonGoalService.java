package com.d1.goalset.modules.goal.service;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;

public interface PersonGoalService {
	Goal write(GoalWritingRequest params);
	
	Goal update(GoalWritingRequest params);
	
	void delete();
}
