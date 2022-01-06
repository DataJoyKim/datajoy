package com.d1.goalset.modules.goal.service;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.GoalSetter;

public interface PersonGoalService {
	Goal write(GoalSetter goalSetter, GoalWritingRequest params);
	
	Goal update(GoalSetter goalSetter, GoalWritingRequest params);
	
	void delete();
}
