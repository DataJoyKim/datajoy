package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	
	public static Goal createGoal(User writer, GoalWritingRequest params) {
		Goal goal = Goal.builder().build();
		
		GoalPlan goalPlan = GoalPlan.createGoalPlan(params);
		return null;
	}

	public Boolean validateSubmit() {
		return null;
	}

	@Builder
	public Goal(Long goalCd, String goalName, Integer weight, GoalWritingState goalWritingStateCd, EvalWay evalWayCd,
			String quantStdMax, String quantStdGoal, String quantStdMin, String qualityStdS, String qualityStdA,
			String qualityStdB, String qualityStdC, String qualityStdD, String contents, Set<GoalPlan> goalPlans) {
		super();
		this.goalCd = goalCd;
		this.goalName = goalName;
		this.weight = weight;
		this.goalWritingStateCd = goalWritingStateCd;
		this.evalWayCd = evalWayCd;
		this.quantStdMax = quantStdMax;
		this.quantStdGoal = quantStdGoal;
		this.quantStdMin = quantStdMin;
		this.qualityStdS = qualityStdS;
		this.qualityStdA = qualityStdA;
		this.qualityStdB = qualityStdB;
		this.qualityStdC = qualityStdC;
		this.qualityStdD = qualityStdD;
		this.contents = contents;
		this.goalPlans = goalPlans;
	}

	public void setGoalPlans(Set<GoalPlan> savedGoalPlans) {
		this.goalPlans = savedGoalPlans;
	}
}
