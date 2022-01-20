package com.d1.goalset.common.exception;

public interface BusinessErrorCode {
	String getCode();
	String getMessage();
	String getErrorKind();
	int getStatus();
}
