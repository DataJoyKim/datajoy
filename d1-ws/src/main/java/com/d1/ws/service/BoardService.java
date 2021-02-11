package com.d1.ws.service;

import java.util.List;
import java.util.Map;

import com.d1.ws.domain.Board;
import com.d1.ws.domain.Project;

public interface BoardService {

	/**
	 * id로 특정 게시글 가져오기
	 * @param boardId - 게시글 번호
	 * @return 특정 게시글
	 */
	Board getBoard(Long boardId);

	/**
	 * 프로젝트에 해당하는 게시글 트리를 가져오기
	 * @param projectId - 프로젝트 번호
	 * @param params - 파라미터
	 * @return 게시글 리스트
	 */
	List<Board> getBoardsTree(Project project, Map<String, String> params);
}
