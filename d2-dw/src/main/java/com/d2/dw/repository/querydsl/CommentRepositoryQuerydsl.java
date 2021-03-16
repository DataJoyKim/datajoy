package com.d2.dw.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Comment;

public interface CommentRepositoryQuerydsl {
	/**
	 * 코멘트 답글 포함하여 가져오기 
	 * @param board - 코멘트에 해당하는 게시글
	 * @param pageable - 페이징
	 * @return 답글포함한 코멘트 리스트
	 */
	Page<Comment> findCommentWithReplyByBoard(Board board, Pageable pageable); 
}
