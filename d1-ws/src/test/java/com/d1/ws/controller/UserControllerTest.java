package com.d1.ws.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest extends BaseControllerTest {

	@Autowired
	UserController userController;
	
    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
         mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
	
	@Test
	public void getUsersTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/users")
					.param("page", "0")
					.param("size", "5")
					.param("sort", "userId,DESC") //구현 필요
				)
				.andDo(print())
				.andExpect(status().isOk()); 
	}
}
