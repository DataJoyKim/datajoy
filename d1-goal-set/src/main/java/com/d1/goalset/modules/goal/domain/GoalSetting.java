package com.d1.goalset.modules.goal.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.Approver;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GoalSetting {
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
	private Approver approver;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "target_id")
	private Set<Goal> goals = new HashSet<>();
	
	public abstract void submit(GoalSettingValidator goalSettingValidator);
	
	public abstract void approve(GoalSettingValidator goalSettingValidator);
	
	public abstract void reject(GoalSettingValidator goalSettingValidator);
	
	public abstract void cancel(GoalSettingValidator goalSettingValidator);
}
