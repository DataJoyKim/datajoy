package com.d2.dw.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO.BoardResponse;
import com.d2.dw.dto.BoardDTO.BoardWriteRequest;

public interface BoardQueryService {

	BoardResponse getBoard(Long boardId);

	Page<BoardResponse> findBoards(Project project, String query, Pageable pageable);

	void deleteBoard(Board board);

	BoardResponse writeTempBoard(String username, Long projectId, BoardWriteRequest params);
}
