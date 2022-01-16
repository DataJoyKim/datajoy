package com.d1.goalset.modules.goal.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class GoalDto {
	
	@Getter @AllArgsConstructor @Builder
	public static class GoalWritingRequest {
		private Long id;
		
		@NotNull
		private String goalName;
		
		@NotNull
		private Integer weight;
		
		@NotNull
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
		
		private Set<GoalPlanWritingDto> goalPlans = new HashSet<>();
	}
	
	@Getter @Builder @AllArgsConstructor
	public static class GoalPlanWritingDto {
		private Long id;
		
		private LocalDate staYmd;
		
		private LocalDate endYmd;
		
		private String plan;
	}
}
