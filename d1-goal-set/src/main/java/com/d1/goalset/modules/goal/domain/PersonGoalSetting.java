package com.d1.goalset.modules.goal.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.GoalSetter;

public class PersonGoalSetting extends GoalSetting {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private GoalSetter goalSetter;

	@Override
	public void submit(GoalSettingValidator goalSettingValidator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void approve(GoalSettingValidator goalSettingValidator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reject(GoalSettingValidator goalSettingValidator) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void cancel(GoalSettingValidator goalSettingValidator) {
		// TODO Auto-generated method stub
		
	}
	 
}
