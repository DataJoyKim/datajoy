package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.user.domain.Approver;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GoalSetting {
	private GoalSettingState goalSettingStatCd;
	private Approver approver;
	private Set<Goal> goals = new HashSet<>();
	
	public abstract void submit();
	
	public abstract void approve();
	
	public abstract void reject();
}
