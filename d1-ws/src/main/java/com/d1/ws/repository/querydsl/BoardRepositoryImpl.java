package com.d1.ws.repository.querydsl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.d1.ws.domain.Board;
import com.d1.ws.domain.Project;
import com.d1.ws.domain.QBoard;
import com.d1.ws.domain.QProject;
import com.d1.ws.domain.QUser;
import com.querydsl.jpa.JPQLQuery;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryQuerydsl {
	
	public BoardRepositoryImpl() {
		super(Board.class);
	}

	@Override
	public Board findByIdQuerydsl(Long id) {
		QBoard qBoard = QBoard.board;
		QProject qProject = QProject.project;
		QUser qUser = QUser.user;
		
		JPQLQuery<Board> query = from(qBoard)
				.leftJoin(qBoard.project, qProject)
				.leftJoin(qBoard.user, qUser)
				.where(qBoard.id.eq(id));
		
		return query.fetchOne();
	}

	@Override
	public List<Board> findBoardTreeByProject(Project project) {
		QBoard qBoard = QBoard.board;
		QProject qProject = QProject.project;
		QUser qUser = QUser.user;
		QBoard qParent = QBoard.board;
		
		JPQLQuery<Board> query = from(qBoard)
				.leftJoin(qBoard.project, qProject)
				.leftJoin(qBoard.user, qUser)
				.leftJoin(qBoard.parent, qParent)
				.where(qBoard.project.eq(project).and(qBoard.parent.isNull()))
				.distinct();
		
		return query.fetch();
	}

}
