package com.d2.dw.validator;

import org.springframework.stereotype.Component;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Comment;
import com.d2.dw.domain.Project;
import com.d2.dw.domain.User;
import com.d2.dw.dto.CommentDTO.CommentWriteRequestDTO;
import com.d2.dw.error.CommentErrorCode;
import com.d2.dw.exception.BusinessException;

@Component("CommentValidator")
public class CommentValidator {

	public void validateWriteComment(User writer, Project project, Board board, CommentWriteRequestDTO params) {
		if(writer == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_WRITER_NULL);
		}
		
		if(project == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_PROJECT_NULL);
		}
		
		if(board == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_BOARD_NULL);
		}
		
		// 프로젝트에 지정된 게시글인지 체크
		if(project.equals(board.getProject()) == false) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_NOT_BOARD_OF_PROJECT);
		}
	}

	
	public void validateUpdateComment(User writer, Project project, Board board, Comment comment, CommentWriteRequestDTO params) {
		if(writer == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_WRITER_NULL);
		}
		
		if(project == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_PROJECT_NULL);
		}
		
		if(board == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_BOARD_NULL);
		}
		
		if(comment == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_COMMENT_NULL);
		}
		
		// 프로젝트에 지정된 게시글인지 체크
		if(project.equals(board.getProject()) == false) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_NOT_BOARD_OF_PROJECT);
		}
		
		// 게시글에 지정된 코멘트인지 체크
		if(board.equals(comment.getBoard()) == false) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_NOT_COMMENT_OF_BOARD);
		}
		
		// 코멘트 작성자인지 체크
		if(writer.equals(comment.getUser()) == false) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_NOT_COMMENT_WRITER);
		}
	}


	public void validateDeleteComment(User writer, Project project, Board board, Comment comment) {
		if(writer == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_WRITER_NULL);
		}
		
		if(project == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_PROJECT_NULL);
		}
		
		if(board == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_BOARD_NULL);
		}
		
		if(comment == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_COMMENT_NULL);
		}
		
		// 프로젝트에 지정된 게시글인지 체크
		if(project.equals(board.getProject()) == false) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_NOT_BOARD_OF_PROJECT);
		}
		
		// 게시글에 지정된 코멘트인지 체크
		if(board.equals(comment.getBoard()) == false) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_NOT_COMMENT_OF_BOARD);
		}
		
		// 코멘트 작성자인지 체크
		if(writer.equals(comment.getUser()) == false) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_NOT_COMMENT_WRITER);
		}
	}

}
