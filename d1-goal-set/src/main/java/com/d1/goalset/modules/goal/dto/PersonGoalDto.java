package com.d1.goalset.modules.goal.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.d1.goalset.modules.goal.code.EvalWay;

import lombok.Getter;

public class PersonGoalDto {
	
	@Getter
	public class GoalWritingRequest {
		private Long id;
		
		private String goalName;
		
		private Integer weight;
		
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
	
	@Getter
	public class GoalPlanWritingDto {
		private Long id;
		
		private LocalDate staYmd;
		
		private LocalDate endYmd;
		
		private String content;
	}
}
