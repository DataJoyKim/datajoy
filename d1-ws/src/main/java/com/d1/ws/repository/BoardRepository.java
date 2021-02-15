package com.d1.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d1.ws.domain.Board;
import com.d1.ws.domain.Project;
import com.d1.ws.repository.querydsl.BoardRepositoryQuerydsl;

@Repository("BoardRepository")
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQuerydsl {

	List<Board> findByProject(Project project);

	Board findByProjectAndId(Project project, Long id);
}
