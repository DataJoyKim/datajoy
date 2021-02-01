package com.d1.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d1.ws.domain.Board;

@Repository("BoardRepository")
public interface BoardRepository extends JpaRepository<Board, Long>{

	Board findById(Long id);
	
}
