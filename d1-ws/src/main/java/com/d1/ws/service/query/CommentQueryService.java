package com.d1.ws.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.d1.ws.domain.Board;
import com.d1.ws.service.dto.CommentDTO;

public interface CommentQueryService {
	/**
	 * 코멘트 리스트 조회
	 * @param board - 코멘트에 해당하는 게시글 Entity
	 * @param pageable - 페이징
	 * @return 코멘트 리스트
	 */
	Page<CommentDTO> findAll(Board board, PageRequest pageable); 
}
