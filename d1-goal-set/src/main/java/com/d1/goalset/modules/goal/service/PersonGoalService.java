package com.d1.goalset.modules.goal.service;

import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.GoalSetter;

public interface PersonGoalService {
	GoalSetting write(GoalSetter goalSetter, GoalWritingRequest params);
	
	GoalSetting updateBy(Long goalCd, GoalSetter goalSetter, GoalWritingRequest params);
	
	void deleteBy(Long goalCd, GoalSetter goalSetter);
	
	void submit(GoalSetter goalSetter);
	
	void cancel(GoalSetter goalSetter);
}
