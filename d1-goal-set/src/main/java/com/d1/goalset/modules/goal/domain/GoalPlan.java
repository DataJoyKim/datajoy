package com.d1.goalset.modules.goal.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalPlan {
	private LocalDate staYmd;
	
	private LocalDate endYmd;
	
	private String content;
	
	public static GoalPlan createGoalPlan(GoalWritingRequest params) {
		GoalPlan goalPlan = GoalPlan.builder().build();
		return goalPlan;
	}

	@Builder
	public GoalPlan(LocalDate staYmd, LocalDate endYmd, String content) {
		super();
		this.staYmd = staYmd;
		this.endYmd = endYmd;
		this.content = content;
	}
}
