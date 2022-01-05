package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.Approver;
import com.d1.goalset.modules.user.domain.GoalSetter;

import lombok.Getter;

@Getter
public abstract class GoalSetting {
	private GoalSettingState goalSettingStatCd;
	private Approver approver;
	private Set<Goal> goals = new HashSet<>();
	
	public abstract Goal write(GoalSetter writer, GoalWritingRequest params);
	
	public abstract void submit();
	
	public abstract void approve();
	
	public abstract void reject();
}
