package com.d2.dw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;

import com.d2.dw.common.BaseTest;
import com.d2.dw.dto.BoardDTO.BoardWriteRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

class BoardControllerTest extends BaseTest { 
	
	@Autowired
	private ObjectMapper mapper;
	
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
	
	@Test
	@DisplayName("게시글 포스팅 테스트")
	public void postingBoardsTest() throws Exception {
		BoardWriteRequestDTO value = BoardWriteRequestDTO.builder()
														.title("제목")
														.content("내용")
														.build();
		
		mockMvc.perform(post("/api/v1/projects/1/boards")
						.content(mapper.writeValueAsString(value))
        				.principal(principal)
						.contentType(MediaTypes.HAL_JSON_VALUE)
						.accept(MediaTypes.HAL_JSON_VALUE)
						)
						.andDo(print())
						.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("게시글 수정 테스트")
	public void updateBoardsTest() throws Exception {
		BoardWriteRequestDTO value = BoardWriteRequestDTO.builder()
														.title("제목111111")
														.content("내용1111")
														.build();
		
		mockMvc.perform(put("/api/v1/projects/1/boards/8")
						.content(mapper.writeValueAsString(value))
        				.principal(principal)
						.contentType(MediaTypes.HAL_JSON_VALUE)
						.accept(MediaTypes.HAL_JSON_VALUE)
						)
						.andDo(print())
						.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("게시글 삭제 테스트")
	public void deleteBoardsTest() throws Exception {
		mockMvc.perform(delete("/api/v1/projects/1/boards/8")
						.principal(principal)
						.contentType(MediaTypes.HAL_JSON_VALUE)
						.accept(MediaTypes.HAL_JSON_VALUE)
						)
						.andDo(print())
						.andExpect(status().isNoContent());
	}
}
