package com.d1.goalset.modules.goal.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalPlanWritingDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder
public class GoalPlan {
	private LocalDate staYmd;
	
	private LocalDate endYmd;
	
	private String content;

	public static GoalPlan createGoalPlan(GoalPlanWritingDto param) {
		GoalPlan goalPlan = GoalPlan.builder()
									.staYmd(param.getStaYmd())
									.endYmd(param.getEndYmd())
									.content(param.getContent())
									.build();
		return goalPlan;
	}
}
