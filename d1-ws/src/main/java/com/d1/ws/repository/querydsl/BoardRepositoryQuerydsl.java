package com.d1.ws.repository.querydsl;

import java.util.List;

import com.d1.ws.domain.Board;
import com.d1.ws.domain.Project;

public interface BoardRepositoryQuerydsl {
	Board findByIdQuerydsl(Long id);
	List<Board> findBoardTreeByProject(Project project);
}
