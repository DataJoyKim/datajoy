package com.d2.dw.repository.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Comment;
import com.d2.dw.domain.Project;
import com.d2.dw.domain.QBoard;
import com.d2.dw.domain.QComment;
import com.d2.dw.domain.QProject;
import com.d2.dw.domain.QUser;
import com.querydsl.jpa.JPQLQuery;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryQuerydsl {

	public CommentRepositoryImpl() {
		super(Comment.class);
	}

	@Override
	public Page<Comment> findCommentsOfBoard(Project project, Board board, Pageable pageable) {
		QComment qComment = QComment.comment1;
		QBoard qBoard = QBoard.board;
		QProject qProject = QProject.project;
		QUser qUser = QUser.user;
		
		JPQLQuery<Comment> query = from(qComment)
									.leftJoin(qComment.board(), qBoard)
									.leftJoin(qBoard.project(), qProject)
									.leftJoin(qBoard.user(), qUser)
									.where(qComment.board().eq(board).and(qBoard.project().eq(project)))
									.distinct();

		List<Comment> results = getQuerydsl()
									.applyPagination(pageable, query) //페이징 적용
									.fetch(); // list type return
		
		return new PageImpl<Comment>(results, pageable, query.fetchCount());
	}
	
}
