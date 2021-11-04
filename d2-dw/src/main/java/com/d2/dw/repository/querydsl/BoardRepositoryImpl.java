package com.d2.dw.repository.querydsl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.QBoard;
import com.d2.dw.domain.QProject;
import com.d2.dw.domain.QUser;
import com.querydsl.jpa.JPQLQuery;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryQuerydsl{

	public BoardRepositoryImpl() {
		super(Board.class);
	}

	@Override
	public Board findBoardById(Long id) {
		QBoard qBoard = QBoard.board;
		QProject qProject = QProject.project;
		QUser qUser = QUser.user;
		
		JPQLQuery<Board> query = from(qBoard)
				.leftJoin(qBoard.project(), qProject)
				.leftJoin(qBoard.user(), qUser)
				.where(qBoard.id.eq(id));
		
		return query.fetchOne();
	}
}
