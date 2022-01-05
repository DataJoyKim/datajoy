package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.User;

import lombok.Getter;

@Getter
public class Goal {
	private Long goalCd;
	
	private String goalName;
	
	private Integer weight;
	
	private GoalWritingState goalWritingStateCd;
	
	private EvalWay evalWayCd;
	
	private String quantStdMax;
	
	private String quantStdGoal;
	
	private String quantStdMin;
	
	private String qualityStdS;
	
	private String qualityStdA;
	
	private String qualityStdB;
	
	private String qualityStdC;
	
	private String qualityStdD;
	
	private String contents;
	
	private Set<GoalPlan> goalPlans = new HashSet<>();
	
	public Goal(User writer, GoalWritingRequest params) {
		
	}

	public Boolean validateSubmit() {
		return null;
	}
}
