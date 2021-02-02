package com.d1.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d1.ws.domain.Board;
import com.d1.ws.domain.Project;

@Repository("BoardRepository")
public interface BoardRepository extends JpaRepository<Board, Long>{

	Board findById(Long id);

	List<Board> findByProject(Project project);
	
}
