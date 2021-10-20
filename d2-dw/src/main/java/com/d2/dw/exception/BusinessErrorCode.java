package com.d2.dw.exception;

public interface BusinessErrorCode {
	String getCode();
	String getMessage();
	String getErrorKind();
	int getStatus();
}
