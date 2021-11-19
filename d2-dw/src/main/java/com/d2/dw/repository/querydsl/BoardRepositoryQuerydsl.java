package com.d2.dw.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;

public interface BoardRepositoryQuerydsl {
	Board findBoardById(Long id);

	Page<Board> findByStatus(BoardStatus status, Pageable pageable);
}
