package com.d1.auth.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import com.d1.auth.common.BaseControllerTest;
import com.d1.auth.dto.SecurityDto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

class SecurityControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("로그인 토큰 발급 테스트")
	public void providTokenTest() throws Exception {
		LoginRequest params = new LoginRequest();
		params.setUsername("ks13ny@naver.com");
		params.setPassword("rlaskrdud1!");
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(params);
		mockMvc.perform(post("/auth/v1/login")
						.contentType("application/json")
						.content(content)
					)
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(header().exists(HttpHeaders.AUTHORIZATION));
	}

}
