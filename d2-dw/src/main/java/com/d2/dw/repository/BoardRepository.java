package com.d2.dw.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;
import com.d2.dw.repository.querydsl.BoardRepositoryQuerydsl;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQuerydsl {

	Page<Board> findByStatus(BoardStatus status, Pageable pageable);

	Board findBoardByIdAndStatus(Long boardId, BoardStatus posting);

}
