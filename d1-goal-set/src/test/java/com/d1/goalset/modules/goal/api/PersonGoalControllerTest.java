package com.d1.goalset.modules.goal.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

class PersonGoalControllerTest extends BaseTest {
	
	@DisplayName("개인목표 내역 조회")
	@Test
	void personGoalsApiTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/person-goals")
				.param("seasonCd", "202201")
				.param("companyCd", "01")
				.param("userId", "1")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("content").exists());
	}

	@DisplayName("특정 개인목표 조회")
	@Test
	void personGoalApiTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/person-goals/3")
				.param("seasonCd", "202201")
				.param("companyCd", "01")
				.param("userId", "1")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("content").exists());
	}
	
	@DisplayName("목표수립 상태 조회")
	@Test
	void personGoalStatusApiTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/person-goals/status")
				.param("seasonCd", "202201")
				.param("companyCd", "01")
				.param("userId", "1")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("content").exists());
	}
	
	@DisplayName("개인목표 생성")
	@Test
	void personGoalPostApiTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		GoalPlanWritingRequest goalPlan_1 = GoalPlanWritingRequest.builder()
															.staYmd(LocalDate.now())
															.endYmd(LocalDate.now())
															.plan("계획합니다.1")
															.build();
		
		GoalPlanWritingRequest goalPlan_2 = GoalPlanWritingRequest.builder()
															.staYmd(LocalDate.now())
															.endYmd(LocalDate.now())
															.plan("계획합니다.2")
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

	@DisplayName("개인목표 수정")
	@Test
	void personGoalPutApiTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		GoalPlanWritingRequest goalPlan_1 = GoalPlanWritingRequest.builder()
															.id(5L)
															.staYmd(LocalDate.now())
															.endYmd(LocalDate.now())
															.plan("계획합니다.1수정")
															.build();
		
		GoalPlanWritingRequest goalPlan_2 = GoalPlanWritingRequest.builder()
															.staYmd(LocalDate.now())
															.endYmd(LocalDate.now())
															.plan("계획합니다.2")
															.build();
		
		List<GoalPlanWritingRequest> goalPlans = new ArrayList<>();
		goalPlans.add(goalPlan_1);
		goalPlans.add(goalPlan_2);
		
		GoalWritingRequest body = GoalWritingRequest.builder()
										.goalName("목표 수정했다!!!!!!!!!!")
										.contents("목표 테스트 내용")
										.evalWayCd(EvalWay.QUALITY_EVAL)
										.quantStdMax("최대")
										.quantStdGoal("목표")
										.quantStdMin("최소")
										.weight(100)
										.goalPlans(goalPlans)
										.build();
		
		this.mockMvc.perform(put("/api/v1/person-goals/7")
				.param("seasonCd", "202201")
				.param("companyCd", "01")
				.param("userId", "2")
				.content(mapper.registerModule(new JavaTimeModule()).writeValueAsString(body))
				.contentType(MediaTypes.HAL_JSON_VALUE)
				)
				.andDo(print())
				.andExpect(status().isNoContent());
	}

	@DisplayName("개인목표 삭제")
	@Test
	void personGoalDeleteApiTest() throws Exception {
		this.mockMvc.perform(delete("/api/v1/person-goals/7")
				.param("seasonCd", "202201")
				.param("companyCd", "01")
				.param("userId", "2")
				.contentType(MediaTypes.HAL_JSON_VALUE)
				)
				.andDo(print())
				.andExpect(status().isNoContent());
	}
}
