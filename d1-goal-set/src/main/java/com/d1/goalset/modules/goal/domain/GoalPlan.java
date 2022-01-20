package com.d1.goalset.modules.goal.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalPlanWritingRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "goal_plan")
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
	
	@Column(name = "plan", columnDefinition = "TEXT")
	private String plan;

	public static GoalPlan createGoalPlan(GoalPlanWritingRequest params) {
		GoalPlan goalPlan = GoalPlan.builder()
									.staYmd(params.getStaYmd())
									.endYmd(params.getEndYmd())
									.plan(params.getPlan())
									.build();
		return goalPlan;
	}

	public static List<GoalPlan> createGoalPlans(List<GoalPlanWritingRequest> goalPlansDto) {
		List<GoalPlan> goalPlans = new ArrayList<>();
		for(GoalPlanWritingRequest goalPlanDto : goalPlansDto) {
			goalPlans.add(createGoalPlan(goalPlanDto));
		}
		
		return goalPlans;
	}

	public void update(GoalPlanWritingRequest params) {
		this.staYmd = params.getStaYmd();
		this.endYmd = params.getEndYmd();
		this.plan = params.getPlan();
	}
	
	public boolean hasData() {
		return (this.id != null) ? true : false;
	}
}
