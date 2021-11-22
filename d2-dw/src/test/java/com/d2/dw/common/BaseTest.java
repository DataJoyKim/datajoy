package com.d2.dw.common;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Disabled
public class BaseTest {
	
	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	private WebApplicationContext ctx;
	
	protected UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken("ks13ny@naver.com", "rlaskrdud1!", null);

	@BeforeEach
	public void setup() {
		// UTF-8 셋팅
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
	            .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
	            .alwaysDo(print())
	            .build();
	}
}
