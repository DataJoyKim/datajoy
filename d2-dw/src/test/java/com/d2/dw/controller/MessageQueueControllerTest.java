package com.d2.dw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.d2.dw.common.BaseTest;

class MessageQueueControllerTest extends BaseTest {

	@Test
	@DisplayName("메시지 큐 테스트")
	public void callMqTest() throws Exception {
		mockMvc.perform(get("/api/queue/test"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
