package com.d1.goalset.modules.goal.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class GoalPlan {
	private LocalDate staYmd;
	
	private LocalDate endYmd;
	
	private String content;
	
	public GoalPlan write() {
		return null;
	}
}
