package com.d1.apigateway.error;

import com.d1.common.exception.BusinessErrorCode;

public enum SecurityErrorCode implements BusinessErrorCode {
	NOT_FOUND_AUTHENTICATION(404, "R001","인증 토큰이 존재하지 않습니다."),
	FAULT_AUTHENTICATION(400, "R002","잘못된 인증토큰 입니다."),
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
