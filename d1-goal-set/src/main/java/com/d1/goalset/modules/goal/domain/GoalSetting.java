package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.user.Org;
import com.d1.goalset.modules.user.User;

import lombok.Getter;

@Getter
public abstract class GoalSetting {
	private GoalSettingState goalSettingStatCd;
	private User approver;
	private Set<Goal> goals = new HashSet<>();
	
	public abstract Goal write(Goal goal, Org writeOrg, User writer);
	
	public abstract void submit();
	
	public abstract void approve();
	
	public abstract void reject();
}
