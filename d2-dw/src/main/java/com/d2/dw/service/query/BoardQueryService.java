package com.d2.dw.service.query;

import java.util.List;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO;
import com.d2.dw.dto.BoardTreeDTO;

public interface BoardQueryService {

	BoardDTO.BoardResponse getBoard(Long boardId);

	List<BoardTreeDTO.BoardTreeResponse> getBoardsTree(Project project);

	Board saveBoard(Board board);

	void deleteBoard(Board board);

}
