package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.GoalSetter;

public class PersonGoalSetting extends GoalSetting {

	@Override
	public Goal write(GoalSettingValidator goalSettingValidator, GoalSetter writer, GoalWritingRequest params) {
		goalSettingValidator.validateWrite(this, writer, params);
		
		return Goal.createGoal(writer, params);
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
