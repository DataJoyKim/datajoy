package com.d1.goalset.modules.goal.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Disabled
public class BaseTest {
	
	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	private WebApplicationContext ctx;
	
	@BeforeEach
	public void setup() {
		// UTF-8 셋팅
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
	            .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
	            .alwaysDo(print())
	            .build();
	}
}
