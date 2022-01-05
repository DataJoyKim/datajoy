package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.Org;
import com.d1.goalset.modules.user.domain.User;

public class PersonGoalSetting extends GoalSetting {

	@Override
	public Goal write(Org writeOrg, User writer, GoalWritingRequest params) {
		
		Goal goal = new Goal(writer, params);
		return null;
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
