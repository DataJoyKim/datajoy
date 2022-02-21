package com.d1.goalset.modules.goal.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberControllerTest extends BaseTest {

	@DisplayName("조직원 조회")
	@Test
	void getMembersApiTest() throws Exception {
		this.mockMvc.perform(get("/goal/api/v1/members")
				.param("seasonCd", "202201")
				.param("companyCd", "01")
				.param("userId", "1")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("content").exists());
	}
}
