package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalPlanWritingDto;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder
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
		Goal goal = Goal.builder()
						.goalName(params.getGoalName())
						.weight(params.getWeight())
						.goalWritingStateCd(GoalWritingState.SAVE)
						.evalWayCd(params.getEvalWayCd())
						.qualityStdS(params.getQualityStdS())
						.qualityStdA(params.getQualityStdA())
						.qualityStdB(params.getQualityStdB())
						.qualityStdC(params.getQualityStdC())
						.qualityStdD(params.getQualityStdD())
						.quantStdMax(params.getQuantStdMax())
						.quantStdGoal(params.getQuantStdGoal())
						.quantStdMin(params.getQuantStdMin())
						.contents(params.getContents())
						.goalPlans(createGoalPlans(params.getGoalPlans()))
						.build();
		
		return goal;
	}

	private static Set<GoalPlan> createGoalPlans(Set<GoalPlanWritingDto> params) {
		Set<GoalPlan> goalPlans = new HashSet<>();
		for(GoalPlanWritingDto param : params) {
			goalPlans.add(GoalPlan.createGoalPlan(param));
		}
		
		return goalPlans;
	}

	public Boolean validateSubmit() {
		return null;
	}

	public void setGoalPlans(Set<GoalPlan> savedGoalPlans) {
		this.goalPlans = savedGoalPlans;
	}
}
