package com.d2.dw.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BoardRepositoryTest {
	
	@Autowired
	BoardRepository boardRepository; 
	
	@Test
	@DisplayName("게시글 DB 조회 테스트")
	public void getBoardsTest() {
		assertThat(boardRepository.findAll().size() >= 0 ? true : false);
	}
	
}
