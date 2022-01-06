package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.GoalSetter;

public class PersonGoalSetting extends GoalSetting {

	@Override
	public Goal write(GoalSetter writer, GoalWritingRequest params) {
		
		Goal goal = Goal.createGoal(writer, params);
		 
		return goal;
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void approve() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reject() {
		// TODO Auto-generated method stub
		
	}
	 
}
