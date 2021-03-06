package com.d1.ws.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BoardControllerTest extends BaseControllerTest{

	@Autowired
	BoardController boardController;
	
    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
         mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }
    
	@Test
	public void getBoardTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/projects/1/boards/1")
			)
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void getProjectBoardsTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/projects/1/boards/tree")
			)
			.andDo(print())
			.andExpect(status().isOk());
	}
}
