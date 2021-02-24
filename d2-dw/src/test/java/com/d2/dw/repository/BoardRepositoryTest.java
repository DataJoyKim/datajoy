package com.d2.dw.repository;

import java.util.List;

import javax.activation.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.d2.dw.domain.Board;

@RunWith(SpringRunner.class)
@DataJpaTest
class BoardRepositoryTest {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BoardRepository boardRepository; 
	
	@Test
	void test() {
		List<Board> boards = boardRepository.findAll();
		
		System.out.println(boards);
	}

}
