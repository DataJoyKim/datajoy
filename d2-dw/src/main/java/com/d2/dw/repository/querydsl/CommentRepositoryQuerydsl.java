package com.d2.dw.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Comment;
import com.d2.dw.domain.Project;

public interface CommentRepositoryQuerydsl {
	/**
	 * 게시글의 코멘트 조회 
	 * @param project - 게시글에 해당하는 프로젝트
	 * @param board - 코멘트에 해당하는 게시글
	 * @param pageable - 페이징
	 * @return 답글포함한 코멘트 리스트
	 */
	Page<Comment> findCommentsOfBoard(Project project, Board board, Pageable pageable); 
}
