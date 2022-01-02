package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import com.d1.goalset.modules.goal.code.GoalSetState;
import com.d1.goalset.modules.user.User;

import lombok.Getter;

@Getter
public abstract class GoalSetting {
	private GoalSetState goalSetStatCd;
	private User approver;
	private Set<Goal> goals = new HashSet<>();
	
	public abstract Goal write();
	
	public abstract void submit();
	
	public abstract void approve();
	
	public abstract void reject();
}
