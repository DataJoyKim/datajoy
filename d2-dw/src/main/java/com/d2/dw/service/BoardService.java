package com.d2.dw.service;

import com.d2.dw.domain.Board;
import com.d2.dw.dto.BoardDTO.BoardResponseDTO;
import com.d2.dw.dto.BoardDTO.BoardWriteRequestDTO;

public interface BoardService {

	/**
	 * 특정 게시글 가져오기
	 * @param boardId
	 * @return
	 */
	Board findBoard(Long boardId);

	/**
	 * 임시게시글 작성하기
	 * @param username
	 * @param projectId
	 * @param params
	 * @return
	 */
	BoardResponseDTO writeTempBoard(String username, Long projectId, BoardWriteRequestDTO params);

	/**
	 * 임시게시글 수정하기
	 * @param username
	 * @param projectId
	 * @param params
	 * @return
	 */
	BoardResponseDTO updateTempBoard(String username, Long projectId, Long boardId, BoardWriteRequestDTO params);
	
	/**
	 * 임시게시글 삭제하기
	 * @param username
	 * @param projectId
	 * @param params
	 * @return
	 */
	void deteleTempBoard(String username, Long projectId, Long boardId);

	/**
	 * 게시글 포스팅하기
	 * @param username
	 * @param projectId
	 * @param params
	 * @return
	 */
	BoardResponseDTO postBoard(String username, Long projectId, BoardWriteRequestDTO params);

	/**
	 * 게시글 수정하기
	 * @param username
	 * @param projectId
	 * @param params
	 * @return
	 */
	BoardResponseDTO updateBoard(String username, Long projectId, Long boardId, BoardWriteRequestDTO params);
	
	/**
	 * 게시글 삭제하기
	 * @param username
	 * @param projectId
	 * @return
	 */
	void deteleBoard(String username, Long projectId, Long boardId);
}
