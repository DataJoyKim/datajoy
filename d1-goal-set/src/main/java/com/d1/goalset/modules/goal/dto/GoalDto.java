package com.d1.goalset.modules.goal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalPlan;
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalSettingResponse;
import com.d1.goalset.modules.user.domain.Approver;
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
		
		private List<GoalPlanWritingRequest> goalPlans = new ArrayList<>();
	}
	
	@Getter @Builder @AllArgsConstructor
	public static class GoalPlanWritingRequest {
		private Long id;
		
		private LocalDate staYmd;
		
		private LocalDate endYmd;
		
		private String plan;
	}
	
	@Getter
	public static class GoalResponse {
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
		
		private List<GoalPlanResponse> goalPlans = new ArrayList<>();

		public void setGoalPlans(List<GoalPlanResponse> goalPlans) {
			this.goalPlans = goalPlans;
		}
		
		public static GoalResponse of(Goal goal) {
			ModelMapper mapper = new ModelMapper();
			
			mapper.createTypeMap(Goal.class, GoalResponse.class)
					.addMappings(mapping -> mapping.skip(GoalResponse::setGoalPlans));
			
			GoalResponse goalResponse = mapper.map(goal, GoalResponse.class);
			List<GoalPlanResponse> goalPlansResponse = GoalPlanResponse.of(goal.getGoalPlans());
			goalResponse.setGoalPlans(goalPlansResponse);
			
			return goalResponse;
		}

		public static List<GoalResponse> of(List<Goal> goals) {
			List<GoalResponse> response = new ArrayList<>();
			for(Goal goal : goals) {
				response.add(GoalResponse.of(goal));
			}
			return response;
		}
	}

	@Getter
	public static class GoalPlanResponse {

		private Long id;
		
		private LocalDate staYmd;
		
		private LocalDate endYmd;
		
		private String plan;
		
		public static List<GoalPlanResponse> of(List<GoalPlan> goalPlans) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	@Getter
	public static class GoalBaseParam {
		@NotNull
		private String seasonCd;
		
		@NotNull
		private String companyCd;
		
		@NotNull
		private Long userId;
	}

	@Getter @Builder @AllArgsConstructor
	public static class GoalSettingResponse {
		private GoalSettingState goalSettingStatCd;

		public static GoalSettingResponse of(PersonGoalSetting personGoalSetting) {
			GoalSettingResponse response = GoalSettingResponse.builder()
														.goalSettingStatCd(personGoalSetting.getGoalSettingStatCd())
														.build();
			
			return response;
		}
	}
}
