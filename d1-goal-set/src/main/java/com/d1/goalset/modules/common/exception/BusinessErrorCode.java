package com.d1.goalset.modules.common.exception;

public interface BusinessErrorCode {
	String getCode();
	String getMessage();
	String getErrorKind();
	int getStatus();
}
