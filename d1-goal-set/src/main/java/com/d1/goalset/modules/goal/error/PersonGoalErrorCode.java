package com.d1.goalset.modules.goal.error;

import com.d1.goalset.common.exception.BusinessErrorCode;

public enum PersonGoalErrorCode implements BusinessErrorCode { 
	NULL_WRITER(400, "R001","작성자가 존재하지 않습니다."), 
	CAN_NOT_WRITE_BY_APPROVAL_STATE(400, "R002","승인되어 작성할수없습니다."), 
	CAN_NOT_WRITE_BY_SUBMIT_STATE(400, "R003","제출하여 작성할수없습니다. 회수 후 작성해주세요."), 
	NOT_RANGE_WEIGHT(400, "R004","가중치 범위가 아닙니다."), 
	NOT_FOUND_GOAL_SETTING(404, "R005","목표수립 데이터가 존재하지 않습니다."), 
	NOT_FOUND_GOAL(404, "R006","목표데이터가 존재하지않습니다."),
	;

	private final String errorKind = "PersonGoal";
	private final String code;
	private final String message;
	private final int status;
	
	PersonGoalErrorCode(final int status,final String code,final String message) {
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
