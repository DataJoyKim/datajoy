package com.d1.goalset.modules.goal.domain;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalWritingState;

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
	
	private GoalPlan goalPlan;
	
	public Boolean validateSubmit() {
		return null;
	}
}
