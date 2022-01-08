package com.d1.goalset.modules.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
	private String code;
	private String message;
	private String errorKind;
	
	public ErrorResponse(String code, String errorKind, String message) {
		this.code = code;
		this.message = message;
		this.errorKind = errorKind;
	}
}
