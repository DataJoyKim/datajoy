package com.d2.dw.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.dto.BoardDTO.BoardResponseDTO;

public interface BoardQueryService {

	/**
	 * 특정 게시글 가져오기
	 * @param boardId
	 * @return
	 */
	BoardResponseDTO findBoard(Long boardId);

	/**
	 * 게시글 리스트 가져오기
	 * 페이징
	 * @param query
	 * @param pageable
	 * @return
	 */
	Page<BoardResponseDTO> findBoards(String query, Pageable pageable);
	
	/**
	 * 특정 임시 게시글 리스트 가져오기
	 * 페이징
	 * @param query
	 * @param pageable
	 * @return
	 */
	BoardResponseDTO findTempBoard(Long boardId);
	
	
	/**
	 * 임시 게시글 리스트 가져오기
	 * 페이징
	 * @param query
	 * @param pageable
	 * @return
	 */
	Page<BoardResponseDTO> findTempBoards(String query, Pageable pageable);

}
