package com.d2.dw.service.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO.BoardResponse;
import com.d2.dw.dto.BoardDTO.SaveBoardRequest;
import com.d2.dw.dto.BoardTreeDTO.BoardTreeResponse;

public interface BoardQueryService {

	BoardResponse getBoard(Long boardId);

	Page<BoardResponse> findBoards(Project project, String query, Pageable pageable);

	List<BoardTreeResponse> getBoardsTree(Project project);

	void deleteBoard(Board board);

	BoardResponse writeBoard(Project project, SaveBoardRequest params);
}
