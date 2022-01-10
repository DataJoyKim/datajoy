package com.d1.goalset.modules.goal.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalPlanWritingDto;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.GoalSetter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder
public class Goal {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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

	public static Goal createGoal(GoalSettingValidator goalSettingValidator, GoalSetting goalSetting, GoalSetter goalSetter,
			GoalWritingRequest params) {
		
		goalSettingValidator.validateCreateGoal(goalSetting, goalSetter, params);
		
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

	public void update(GoalSettingValidator goalSettingValidator, GoalSetting goalSetting, GoalSetter goalSetter,
			GoalWritingRequest params) {
		goalSettingValidator.validateUpdateGoal(this, goalSetting, goalSetter, params);
		
		this.goalName = params.getGoalName();
		this.weight = params.getWeight();
		this.goalWritingStateCd = GoalWritingState.SAVE;
		this.evalWayCd = params.getEvalWayCd();
		this.qualityStdS = params.getQualityStdS();
		this.qualityStdA = params.getQualityStdA();
		this.qualityStdB = params.getQualityStdB();
		this.qualityStdC = params.getQualityStdC();
		this.qualityStdD = params.getQualityStdD();
		this.quantStdMax = params.getQuantStdMax();
		this.quantStdGoal = params.getQuantStdGoal();
		this.quantStdMin = params.getQuantStdMin();
		this.contents = params.getContents();
		
		Map<Long, GoalPlan> goalPlanMap = createGoalPlanMap(this.goalPlans);
		
		saveGoalPlans(params.getGoalPlans(), goalPlanMap);
	}

	public void delete(GoalSettingValidator goalSettingValidator, GoalSetting goalSetting, GoalSetter goalSetter) {
		goalSettingValidator.validateDeleteGoal(this, goalSetting, goalSetter);
		
		this.goalWritingStateCd = GoalWritingState.DELETE;
	}

	private void saveGoalPlans(Set<GoalPlanWritingDto> goalPlans, Map<Long, GoalPlan> goalPlanMap) {
		for(GoalPlanWritingDto param : goalPlans) {
			if(goalPlanMap.containsKey(param.getId())) {
				GoalPlan goalPlan = goalPlanMap.get(param.getId());
				goalPlan.update(param);
			}
			else {
				this.goalPlans.add(GoalPlan.createGoalPlan(param));
			}
		}
	}

	private Map<Long, GoalPlan> createGoalPlanMap(Set<GoalPlan> goalPlans) {
		Map<Long, GoalPlan> goalPlanMap = new HashMap<>();
		for(GoalPlan goalPlan : goalPlans) {
			goalPlanMap.put(goalPlan.getId(), goalPlan);
		}
		
		return goalPlanMap;
	}

	private static Set<GoalPlan> createGoalPlans(Set<GoalPlanWritingDto> params) {
		Set<GoalPlan> goalPlans = new HashSet<>();
		for(GoalPlanWritingDto param : params) {
			goalPlans.add(GoalPlan.createGoalPlan(param));
		}
		
		return goalPlans;
	}

	public void setGoalPlans(Set<GoalPlan> savedGoalPlans) {
		this.goalPlans = savedGoalPlans;
	}
}
