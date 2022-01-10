package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.Approver;
import com.d1.goalset.modules.user.domain.GoalSetter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GoalSetting {
	private GoalSetter goalSetter;
	private GoalSettingState goalSettingStatCd;
	private Approver approver;
	private Set<Goal> goals = new HashSet<>();
	
	public abstract void submit(GoalSettingValidator goalSettingValidator);
	
	public abstract void approve(GoalSettingValidator goalSettingValidator);
	
	public abstract void reject(GoalSettingValidator goalSettingValidator);
	
	public abstract void cancel(GoalSettingValidator goalSettingValidator);
}
