package com.d1.goalset.modules.goal.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.d1.goalset.modules.goal.validator.GoalSettingValidator;

@Entity
@Table(name = "goal_setting")
public class OrgGoalSetting extends GoalSetting {

	@Override
	public void submit(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void approve(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reject(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		// TODO Auto-generated method stub
		
	}

	
}
