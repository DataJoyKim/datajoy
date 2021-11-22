package com.d2.dw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.repository.querydsl.BoardRepositoryQuerydsl;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQuerydsl {

	Board findBoardByIdAndStatus(Long boardId, BoardStatus posting);

	Optional<Board> findByIdAndProject(Long boardId, Project project);

}
