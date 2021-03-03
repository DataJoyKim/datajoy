package com.d2.dw.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
class BoardRepositoryTest {
	
	@Autowired
	BoardRepository boardRepository; 
	
	@Test
	public void findBoardTreeByProjectTest() {
		Project project = new Project();
		project.setId((long) 1);
		
		List<Board> boards = boardRepository.findBoardTreeByProject(project);
		
		assertNotNull(boards);
	}
	
}
