package com.d1.common.exception;

public interface BusinessErrorCode {
	String getCode();
	String getMessage();
	String getErrorKind();
	int getStatus();
}
