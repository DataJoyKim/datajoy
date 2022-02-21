package com.d1.goalset.modules.goal.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalPlanWritingRequest;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "goal")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder
public class Goal {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goal_id")
	private Long id;
	
	@Column(name = "goal_name")
	private String goalName;

	@Column(name = "season_cd")
	private String seasonCd;
	
	@Column(name = "company_cd")
	private String companyCd;

	@Column(name = "target_id")
	private Long targetId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "goal_type")
	private GoalTypeCode goalType;
	
	@Column(name = "weight", columnDefinition = "TINYINT")
	private Integer weight;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "goal_writing_state_cd")
	private GoalWritingState goalWritingStateCd;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "eval_way_cd")
	private EvalWay evalWayCd;
	
	@Column(name = "quant_std_max", columnDefinition = "TEXT")
	private String quantStdMax;
	
	@Column(name = "quant_std_goal", columnDefinition = "TEXT")
	private String quantStdGoal;
	
	@Column(name = "quant_std_min", columnDefinition = "TEXT")
	private String quantStdMin;
	
	@Column(name = "quality_std_s", columnDefinition = "TEXT")
	private String qualityStdS;
	
	@Column(name = "quality_std_a", columnDefinition = "TEXT")
	private String qualityStdA;
	
	@Column(name = "quality_std_b", columnDefinition = "TEXT")
	private String qualityStdB;
	
	@Column(name = "quality_std_c", columnDefinition = "TEXT")
	private String qualityStdC;
	
	@Column(name = "quality_std_d", columnDefinition = "TEXT")
	private String qualityStdD;
	
	@Lob
	@Column(name = "contents")
	private String contents;
	
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "goal_id")
	private List<GoalPlan> goalPlans = new ArrayList<>();

	/**
	 * 목표 생성하기
	 * @param goalType
	 * @param setter
	 * @param goalPlans
	 * @param params
	 * @return
	 */
	public static Goal createGoal(GoalType goalType, User setter, List<GoalPlan> goalPlans, GoalWritingRequest params) {
		
		Goal goal = Goal.builder()
						.goalName(params.getGoalName())
						.seasonCd(setter.getSeasonCd())
						.companyCd(setter.getCompanyCd())
						.targetId(goalType.getTargetId(setter))
						.goalType(goalType.getGoalTypeCode())
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
						.goalPlans(goalPlans)
						.build();
		
		return goal;
	}

	public void update(GoalSettingValidator goalSettingValidator, GoalSetting goalSetting, User setter,
			GoalWritingRequest params) {
		goalSettingValidator.validateUpdateGoal(this, goalSetting, setter, params);
		
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
		
		saveGoalPlans(params.getGoalPlans(), createGoalPlanMap(this.goalPlans));
	}

	public void delete(GoalSettingValidator goalSettingValidator, GoalSetting goalSetting, User setter) {
		goalSettingValidator.validateDeleteGoal(this, goalSetting, setter);
		
		this.goalWritingStateCd = GoalWritingState.DELETE;
	}

	private void saveGoalPlans(List<GoalPlanWritingRequest> goalPlans, Map<Long, GoalPlan> goalPlanMap) {
		for(GoalPlanWritingRequest param : goalPlans) {
			if(goalPlanMap.containsKey(param.getId())) {
				GoalPlan goalPlan = goalPlanMap.get(param.getId());
				goalPlan.update(param);
			}
			else {
				this.goalPlans.add(GoalPlan.createGoalPlan(param));
			}
		}
	}

	private Map<Long, GoalPlan> createGoalPlanMap(List<GoalPlan> goalPlans) {
		Map<Long, GoalPlan> goalPlanMap = new HashMap<>();
		for(GoalPlan goalPlan : goalPlans) {
			goalPlanMap.put(goalPlan.getId(), goalPlan);
		}
		
		return goalPlanMap;
	}
}
