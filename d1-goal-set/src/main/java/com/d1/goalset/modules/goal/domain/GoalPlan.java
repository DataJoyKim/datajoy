package com.d1.goalset.modules.goal.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalPlanWritingDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder
public class GoalPlan {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goal_plan_id")
	private Long id;
	
	@Column(name = "sta_ymd")
	private LocalDate staYmd;
	
	@Column(name = "end_ymd")
	private LocalDate endYmd;
	
	@Column(name = "plan")
	private String plan;

	public static GoalPlan createGoalPlan(GoalPlanWritingDto params) {
		GoalPlan goalPlan = GoalPlan.builder()
									.staYmd(params.getStaYmd())
									.endYmd(params.getEndYmd())
									.plan(params.getPlan())
									.build();
		return goalPlan;
	}

	public void update(GoalPlanWritingDto params) {
		this.staYmd = params.getStaYmd();
		this.endYmd = params.getEndYmd();
		this.plan = params.getPlan();
	}
}
