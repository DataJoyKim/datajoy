package com.d1.goalset.modules.goal.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.GoalSetter;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "goal_setting")
@Getter @Setter
public class PersonGoalSetting extends GoalSetting {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "target_id")
	private GoalSetter goalSetter;

	@Override
	public void submit(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		calculateSumWeight(goals);
		
		goalSettingValidator.validateSubmit(this);
		
		this.setGoalSettingStatCd(GoalSettingState.SUBMIT);
	}

	@Override
	public void approve(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		this.setGoalSettingStatCd(GoalSettingState.APPROVAL);
	}

	@Override
	public void reject(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		this.setGoalSettingStatCd(GoalSettingState.REJECTION);
	}

	@Override
	public void cancel(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		this.setGoalSettingStatCd(GoalSettingState.CANCEL);
	}

	private void calculateSumWeight(List<Goal> goals) {
		for(Goal goal : goals) {
			this.setSumWeight(this.getSumWeight() + goal.getWeight());
		}
	}
}
