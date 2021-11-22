package com.d2.dw.error;

import com.d2.dw.exception.BusinessErrorCode;

public enum CommentErrorCode implements BusinessErrorCode {
	FAULT_REQUEST_BY_PROJECT_NULL(400, "R001","잘못요청하였습니다. 존재하지 않는 프로젝트입니다."),
	FAULT_REQUEST_BY_BOARD_NULL(400, "R002","잘못요청하였습니다. 존재하지 않는 게시글입니다."), 
	FAULT_REQUEST_BY_WRITER_NULL(400, "R003","잘못요청하였습니다. 존재하지 않는 작성자입니다."), 
	FAULT_REQUEST_BY_COMMENT_NULL(400, "R004","잘못요청하였습니다. 존재하지 않는 댓글입니다."), 
	FAULT_REQUEST_BY_NOT_COMMENT_WRITER(400, "R005","잘못요청하였습니다. 댓글 작성자가 아닙니다."), 
	FAULT_REQUEST_BY_NOT_BOARD_OF_PROJECT(400, "R006","잘못요청하였습니다. 프로젝트의 게시글이 아닙니다."),
	FAULT_REQUEST_BY_NOT_COMMENT_OF_BOARD(400, "R007","잘못요청하였습니다. 게시글의 댓글이 아닙니다."),
	;

	private final String errorKind = "Comment";
	private final String code;
	private final String message;
	private final int status;
	
	CommentErrorCode(final int status,final String code,final String message) {
		this.code = code;
		this.message = message;
		this.status = status;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public String getErrorKind() {
		return this.errorKind;
	}

	@Override
	public int getStatus() {
		return this.status;
	}
}
