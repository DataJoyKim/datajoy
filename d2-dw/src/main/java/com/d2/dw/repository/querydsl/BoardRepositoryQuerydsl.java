package com.d2.dw.repository.querydsl;

import java.util.List;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;

public interface BoardRepositoryQuerydsl {
	Board findBoardById(Long id);
	
	List<Board> findBoardTreeByProject(Project project);
}
