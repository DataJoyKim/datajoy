package com.d2.dw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.d2.dw.common.BaseTest;

class BoardControllerTest extends BaseTest { 

	@Test
	@DisplayName("특정 게시글 가져오기 테스트")
	public void getBoardTest() throws Exception {
		mockMvc.perform(get("/api/v1/projects/1/boards/1")
					)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("content").exists());
	}

	@Test
	@DisplayName("게시글 리스트 가져오기 테스트")
	public void getBoardsTest() throws Exception {
		mockMvc.perform(get("/api/v1/projects/1/boards")
					.param("page", "1")
					.param("size", "2")
					.param("query", "")
					)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("_links").exists())
					.andExpect(jsonPath("page").exists());
	}
}
