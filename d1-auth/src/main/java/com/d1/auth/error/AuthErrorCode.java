package com.d1.auth.error;

import com.d1.common.exception.BusinessErrorCode;

public enum AuthErrorCode implements BusinessErrorCode {
	FAULT_ID_AND_PWD(400, "R001","아이디 또는 비밀번호가 잘못되었습니다."), 
	FAILED_AUTH_TOKEN(500, "R002","인증토큰 생성에 실패하였습니다."),
	;

	private final String errorKind = "Auth";
	private final String code;
	private final String message;
	private final int status;
	
	AuthErrorCode(final int status,final String code,final String message) {
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
