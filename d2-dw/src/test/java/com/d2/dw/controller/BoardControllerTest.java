package com.d2.dw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BoardControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getBoards() throws Exception {
		this.mockMvc.perform(get("/api/v1/projects/1/boards/1")
					)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("content").exists());
	}

	@Test
	public void getBoardTree() throws Exception {
		this.mockMvc.perform(get("/api/v1/projects/1/boards/tree")
					)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("content").exists());
		
	}
}
