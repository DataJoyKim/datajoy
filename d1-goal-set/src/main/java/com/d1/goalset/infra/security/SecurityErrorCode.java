package com.d1.goalset.infra.security;

import com.d1.goalset.common.exception.BusinessErrorCode;

public enum SecurityErrorCode implements BusinessErrorCode { 
	FAILED_AUTHENTICATION(401, "R001","인증에 실패하였습니다."), 
	;

	private final String errorKind = "Security";
	private final String code;
	private final String message;
	private final int status;
	
	SecurityErrorCode(final int status,final String code,final String message) {
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
