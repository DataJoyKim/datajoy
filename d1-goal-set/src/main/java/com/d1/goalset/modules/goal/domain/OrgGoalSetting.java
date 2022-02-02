package com.d1.goalset.modules.goal.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.Org;

@Entity
@Table(name = "goal_setting")
public class OrgGoalSetting extends GoalSetting {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "target_id")
	private Org org;
	
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
