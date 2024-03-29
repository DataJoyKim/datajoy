package com.d1.goalset.modules.goal.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalPlanWritingRequest;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

class MemberPersonGoalControllerTest extends BaseTest {

	@DisplayName("조직원 조회")
	@Test
	void getMembersApiTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/members")
				.param("seasonCd", "202201")
				.param("companyCd", "01")
				.param("userId", "1")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("content").exists());
	}

	@DisplayName("개인목표 승인 생성")
	@Test
	void personGoalApprovalApiTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		GoalPlanWritingRequest goalPlan_1 = GoalPlanWritingRequest.builder()
															.staYmd(LocalDate.now())
															.endYmd(LocalDate.now())
															.plan("계획1") 
															.build();
		
		GoalPlanWritingRequest goalPlan_2 = GoalPlanWritingRequest.builder()
															.staYmd(LocalDate.now())
															.endYmd(LocalDate.now())
															.plan("계획2")
															.build();
		
		List<GoalPlanWritingRequest> goalPlans = new ArrayList<>();
		goalPlans.add(goalPlan_1);
		goalPlans.add(goalPlan_2);
		
		GoalWritingRequest body = GoalWritingRequest.builder()
										.goalName("목표1")
										.contents("목표 테스트 내용")
										.evalWayCd(EvalWay.QUALITY_EVAL) 
										.quantStdMax("최대")
										.quantStdGoal("목표")
										.quantStdMin("최소")
										.weight(100)
										.goalPlans(goalPlans)
										.build();
		
		this.mockMvc.perform(post("/api/v1/person-goals")
				.param("seasonCd", "202201")
				.param("companyCd", "01")
				.param("userId", "2")
				.content(mapper.registerModule(new JavaTimeModule()).writeValueAsString(body))
				.contentType(MediaTypes.HAL_JSON_VALUE)
				)
				.andDo(print())
				.andExpect(status().isCreated());
	}
}
