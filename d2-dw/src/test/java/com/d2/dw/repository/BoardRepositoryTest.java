package com.d2.dw.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.d2.dw.domain.Board;

@RunWith(SpringRunner.class)
@SpringBootTest
class BoardRepositoryTest {
	
	@Autowired
	BoardRepository boardRepository; 
	
	@Test
	void test() {
		List<Board> boards = boardRepository.findAll();
		
		System.out.println(boards);
	}
	
}
