package com.d1.goalset.modules.goal.domain;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Entity
@Table(name = "goal_setting")
@Getter @AllArgsConstructor @Builder
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
	private GoalSettingState goalSettingStatCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approver_id")
	private User approver;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "setter_id")
	private User setter;

	@Column(name = "target_id")
	private Long targetId;
	
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumns({ 
		@JoinColumn(name = "target_id", referencedColumnName = "target_id"),
		@JoinColumn(name = "season_cd", referencedColumnName = "season_cd"),
		@JoinColumn(name = "company_cd", referencedColumnName = "company_cd"),
		@JoinColumn(name = "goal_type", referencedColumnName = "goal_type")
	})
	private List<Goal> goals = new ArrayList<>();
	
	@Transient
	private Integer sumWeight;

	public void submit(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		calculateSumWeight(goals);
		
		goalSettingValidator.validateSubmit(this);
		
		this.goalSettingStatCd = GoalSettingState.SUBMIT;
	}

	public void approve(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		this.goalSettingStatCd = GoalSettingState.APPROVAL;
	}

	public void reject(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		this.goalSettingStatCd = GoalSettingState.REJECTION;
	}

	public void cancel(GoalSettingValidator goalSettingValidator, List<Goal> goals) {
		this.goalSettingStatCd = GoalSettingState.CANCEL;
	}

	public void writeGoal(GoalSettingValidator goalSettingValidator, User writer, Goal goal, GoalWritingRequest params) {
		goalSettingValidator.validateCreateGoal(this, writer, params);

		this.goalSettingStatCd = GoalSettingState.SETTING;
		getGoals().add(goal);
	}

	private void calculateSumWeight(List<Goal> goals) {
		for(Goal goal : goals) {
			this.sumWeight += goal.getWeight();
		}
	}

	public static List<Long> createBatchSetterIds(List<GoalSetting> goalSettingOfMembers) {
		List<Long> ids = new ArrayList<>();
		
		for(GoalSetting goal : goalSettingOfMembers) {
			if(goal.getSetter() != null) {
				ids.add(goal.getId());
			}
		}
		
		return ids;
	}
}
