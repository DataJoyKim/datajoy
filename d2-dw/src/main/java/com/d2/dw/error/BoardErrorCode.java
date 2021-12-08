package com.d2.dw.error;

import com.d2.dw.exception.BusinessErrorCode;

public enum BoardErrorCode implements BusinessErrorCode {
	NOT_FOUND_PROJECT(404, "R001","프로젝트 데이터가 존재하지 않습니다."), 
	NOT_FOUND_WRITER(404, "R002","작성자 데이터가 존재하지 않습니다."), 
	FAULT_REQUEST_BY_CONTENTS_NULL(400, "R003","내용을 입력해야합니다."),
	FAULT_REQUEST_BY_TITLE_NULL(400, "R004","제목을 입력해야합니다."), 
	FAULT_REQUEST_BY_NOT_FOUND_PROJECT(400, "R004","잘못요청하였습니다. 프로젝트에 존재하지 않는 게시글입니다."),
	;

	private final String errorKind = "Board";
	private final String code;
	private final String message;
	private final int status;
	
	BoardErrorCode(final int status,final String code,final String message) {
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
