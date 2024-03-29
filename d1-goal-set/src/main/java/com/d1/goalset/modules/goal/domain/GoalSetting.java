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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * aggregate root
 * @author 김낙영
 *
 */
@Entity
@Table(name = "goal_setting")
@Getter 
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder
public class GoalSetting {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goal_setting_id")
	private Long id;
	
	@Column(name = "season_cd")
	private String seasonCd;
	
	@Column(name = "company_cd")
	private String companyCd;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "goal_type")
	private GoalTypeCode goalType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "goal_setting_state_cd")
	private GoalSettingState goalSettingStateCd;

	@Column(name = "target_id")
	private Long targetId;
	
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "goal_setting_id")
	private List<Goal> goals = new ArrayList<>();
	
	@Transient
	@Builder.Default
	private Integer sumWeight = 0;

	public void submit(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		calculateSumWeight(goals);
		
		goalSettingValidator.validateSubmit(this, goals);
		
		this.goalSettingStateCd = GoalSettingState.SUBMIT;
	}

	public void approve(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		
		goalSettingValidator.validateApproval(this);
		
		this.goalSettingStateCd = GoalSettingState.APPROVAL;
	}

	public void reject(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		
		goalSettingValidator.validateRejection(this);
		
		this.goalSettingStateCd = GoalSettingState.REJECTION;
	}

	public void cancel(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		
		goalSettingValidator.validateCancel(this);
		
		this.goalSettingStateCd = GoalSettingState.CANCEL;
	}

	public void writeGoal(GoalSettingValidator goalSettingValidator, User writer, Goal goal) {
		goalSettingValidator.validateCreateGoal(this, writer, goal);

		this.goalSettingStateCd = GoalSettingState.SETTING;
		this.goals.add(goal);
	}

	private void calculateSumWeight(List<Goal> goals) {
		for(Goal goal : goals) {
			this.sumWeight += goal.getWeight();
		}
	}

	public static Map<Long, GoalSetting> createGoalSettingMap(List<GoalSetting> goalSettings) {
		Map<Long, GoalSetting> map = new HashMap<>();
		for(GoalSetting goalSetting : goalSettings) {
			map.put(goalSetting.getId(), goalSetting);
		}
		return map;
	}
}
