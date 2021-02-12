package com.d1.ws.service;

import com.d1.ws.domain.Board;

public interface BoardService {

	/**
	 * id로 특정 게시글 가져오기
	 * @param boardId - 게시글 번호
	 * @return 특정 게시글
	 */
	Board findById(Long id);
}
