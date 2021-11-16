package com.d2.dw.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.domain.Board;
import com.d2.dw.dto.BoardDTO.BoardWriteRequest;

public interface BoardService {

	/**
	 * 특정 게시글 가져오기
	 * @param boardId
	 * @return
	 */
	Board findBoard(Long boardId);

	/**
	 * 게시글 리스트 가져오기
	 * 페이징
	 * @param query
	 * @param pageable
	 * @return
	 */
	Page<Board> findBoards(String query, Pageable pageable);
	
	/**
	 * 특정 임시 게시글 리스트 가져오기
	 * 페이징
	 * @param query
	 * @param pageable
	 * @return
	 */
	Board findTempBoard(Long boardId);
	
	
	/**
	 * 임시 게시글 리스트 가져오기
	 * 페이징
	 * @param query
	 * @param pageable
	 * @return
	 */
	Page<Board> findTempBoards(String query, Pageable pageable);

	/**
	 * 임시게시글 작성하기
	 * @param username
	 * @param projectId
	 * @param params
	 * @return
	 */
	Board writeTempBoard(String username, Long projectId, BoardWriteRequest params);

	/**
	 * 임시게시글 수정하기
	 * @param username
	 * @param projectId
	 * @param params
	 * @return
	 */
	Board updateTempBoard(String username, Long projectId, Long boardId, BoardWriteRequest params);
	
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
	Board postBoard(String username, Long projectId, BoardWriteRequest params);

	/**
	 * 게시글 수정하기
	 * @param username
	 * @param projectId
	 * @param params
	 * @return
	 */
	Board updateBoard(String username, Long projectId, Long boardId, BoardWriteRequest params);
	
	/**
	 * 게시글 삭제하기
	 * @param username
	 * @param projectId
	 * @return
	 */
	void deteleBoard(String username, Long projectId, Long boardId);
}
