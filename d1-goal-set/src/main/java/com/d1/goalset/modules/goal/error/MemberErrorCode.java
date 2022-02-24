package com.d1.goalset.modules.goal.error;

import com.d1.goalset.common.exception.BusinessErrorCode;

public enum MemberErrorCode implements BusinessErrorCode { 
	NOT_FOUND_MEMBER(404, "R001","존재하지않는 조직원입니다."), 
	FAULT_APPROVER(400, "R002","요청한 조직원의 승인자가 아닙니다."), 
	NOT_FOUND_GOAL_OF_MEMBER(404, "R003","조직원의 목표가 존재하지 않습니다."), 
	NOT_FOUND_GOAL_SETTING(404, "R004","목표수립 데이터가 존재하지 않습니다."),
	;

	private final String errorKind = "Member";
	private final String code;
	private final String message;
	private final int status;
	
	MemberErrorCode(final int status,final String code,final String message) {
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
