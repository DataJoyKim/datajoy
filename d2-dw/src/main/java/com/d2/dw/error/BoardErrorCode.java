package com.d2.dw.error;

import lombok.Getter;

@Getter
public enum BoardErrorCode {
	NOT_FOUND_PROJECT("R001","프로젝트 데이터가 존재하지 않습니다."),
	;
	
	private String code;
	private String message;
	
	BoardErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
