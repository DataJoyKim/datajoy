package com.d2.dw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.d2.dw.common.BaseTest;

class ProjectControllerTest extends BaseTest {

	@Test
	@DisplayName("본인 프로젝트 조회")
	public void getMyProjects() throws Exception {
		mockMvc.perform(get("/api/v1/projects"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("_links").exists());
	}

}
