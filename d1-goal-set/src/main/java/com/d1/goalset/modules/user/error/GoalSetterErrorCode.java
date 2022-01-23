package com.d1.goalset.modules.user.error;

import com.d1.goalset.common.exception.BusinessErrorCode;

public enum GoalSetterErrorCode implements BusinessErrorCode { 
	NULL_GOAL_SETTER(404, "R001","목표수립자가 존재하지 않습니다."), 
	;

	private final String errorKind = "PersonGoal";
	private final String code;
	private final String message;
	private final int status;
	
	GoalSetterErrorCode(final int status,final String code,final String message) {
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
