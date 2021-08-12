package com.d2.dw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.d2.dw.common.BaseTest;

class CommentControllerTest extends BaseTest {
	
	@Test
	@DisplayName("게시글에 매핑된 코멘트전체 가져오기 테스트")
	public void getCommentsTest() throws Exception {
		mockMvc.perform(get("/api/v1/projects/1/boards/1/comments")
					.param("page", "1")
					.param("size", "2")
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("_links").exists())
				.andExpect(jsonPath("page").exists())
				.andExpect(jsonPath("_embedded.commentResourceList[0]._links.self").exists());
	}

}
