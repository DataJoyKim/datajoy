package com.d1.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.d1.ws.domain.Board;
import com.d1.ws.domain.Project;

@Repository("BoardRepository")
public interface BoardRepository extends JpaRepository<Board, Long>{

	Board findById(Long id);

	List<Board> findByProject(Project project);

	@Query(	"select distinct b "
			+ " from Board b"
			+ " left join fetch b.project"
			+ " left join fetch b.user"
			+ " left join fetch b.parent"
			+ " left join fetch b.childList"
			+ " where b.project = :project"
			+ " and b.parent IS NULL")
	List<Board> findBoardTreeByProject(@Param("project") Project project);
}
