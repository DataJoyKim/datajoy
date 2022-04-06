package com.d1.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -4390598921662151749L;
	
	private String errorKind;
	private String code;
	private String message;
	private int status;
	
	public BusinessException(BusinessErrorCode errorCode) {
		this(errorCode.getStatus(), errorCode.getErrorKind(), errorCode.getCode(), errorCode.getMessage());
	}

	public BusinessException(int status, String errorKind, String code, String message) {
		super(message);
		this.errorKind = errorKind;
		this.code = code;
		this.status = status;
		this.message = message;
	}
}
