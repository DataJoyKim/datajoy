package com.d2.dw.repository.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.d2.dw.code.BoardStatus;
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

	@Override
	public Page<Board> findByStatus(BoardStatus status, Pageable pageable) {
		QBoard qBoard = QBoard.board;
		QProject qProject = QProject.project;
		QUser qUser = QUser.user;
		
		JPQLQuery<Board> query = from(qBoard)
									.leftJoin(qBoard.project(), qProject)
									.leftJoin(qBoard.user(), qUser)
									.where(qBoard.status.eq(status))
									.distinct();
		
		long totalCnt = query.fetchCount();
		
		List<Board> results = getQuerydsl()
									.applyPagination(pageable, query) //페이징 적용
									.fetch(); // list type return
		
		return new PageImpl<Board>(results, pageable, totalCnt);
	}
}
