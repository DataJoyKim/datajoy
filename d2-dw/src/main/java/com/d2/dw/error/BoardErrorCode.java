package com.d2.dw.error;

import com.d2.dw.exception.BusinessErrorCode;

public enum BoardErrorCode implements BusinessErrorCode {
	NOT_FOUND_PROJECT(404, "R001","프로젝트 데이터가 존재하지 않습니다."),
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
