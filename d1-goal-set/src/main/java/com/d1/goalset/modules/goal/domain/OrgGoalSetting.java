package com.d1.goalset.modules.goal.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.d1.goalset.modules.goal.validator.GoalSettingValidator;

@Entity
@Table(name = "goal_setting")
public class OrgGoalSetting extends GoalSetting {

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
