package com.d1.goalset.modules.goal.error;

import com.d1.goalset.common.exception.BusinessErrorCode;

public enum PersonGoalErrorCode implements BusinessErrorCode { 
	NULL_WRITER(400, "R001","작성자가 존재하지 않습니다."), 
	CAN_NOT_WRITE_BY_APPROVAL_STATE(400, "R002","승인되어 작성할수없습니다."), 
	CAN_NOT_WRITE_BY_SUBMIT_STATE(400, "R003","제출하여 작성할수없습니다. 회수 후 작성해주세요."), 
	NOT_RANGE_WEIGHT(400, "R004","가중치 범위가 아닙니다."), 
	NOT_FOUND_GOAL_SETTING(404, "R005","목표수립 데이터가 존재하지 않습니다."), 
	NOT_FOUND_GOAL(404, "R006","목표데이터가 존재하지않습니다."), 
	CAN_NOT_UPDATE_BY_DELETE_STATE(400, "R007","삭제상태에서는 목표를 수정 할 수 없습니다."), 
	CAN_NOT_DELETE_BY_DELETE_STATE(400, "R008","삭제상태에서는 목표를 삭제 할 수 없습니다."),
	CAN_NOT_UPDATE_BY_APPROVAL_STATE(400, "R009","승인되어 수정 할 수 없습니다."), 
	CAN_NOT_UPDATE_BY_SUBMIT_STATE(400, "R010","제출하여 수정 할 수 없습니다. 회수 후 수정해주세요."),
	CAN_NOT_DELETE_BY_APPROVAL_STATE(400, "R011","승인되어 삭제 할 수 없습니다."), 
	CAN_NOT_DELETE_BY_SUBMIT_STATE(400, "R012","제출하여 삭제 할 수 없습니다. 회수 후 삭제해주세요."), 
	NOT_FAULT_VALUE_BY_SUM_WEIGHT(400, "R013","가중치의 합이 100이 아닙니다."), 
	CAN_NOT_SUBMIT_BY_APPROVAL_STATE(400, "R014","승인되어 제출 할 수 없습니다."),
	CAN_NOT_SUBMIT_BY_SUBMIT_STATE(400, "R015","이미 제출하였습니다."), 
	CAN_NOT_APPROVE_BY_NOT_SUBMIT_STATE(400, "R016","제출 상태에서만 승인할수있습니다."),
	CAN_NOT_REJECT_BY_NOT_SUBMIT_STATE(400, "R017","제출 상태에서만 반려할수있습니다."), 
	CAN_NOT_COLLECT_BY_NOT_SUBMIT_STATE(400, "R017","제출 상태에서만 회수 가능합니다."), 
	NULL_QUALITY_STD(400, "R018","정성평가 기준이 입력되지않는 목표가 존재합니다."), 
	NULL_QUANT_STD(400, "R019","정량평가 기준이 입력되지않는 목표가 존재합니다."), 
	NULL_EVAL_WAY_CD(400, "R020","평가방식이 선택되지않는 목표가 존재합니다."), 
	NULL_GOAL_NAME(400, "R021","목표명이 입려되지않는 목표가 존재합니다."), 
	NULL_CONTENTS(400, "R022","내용이 입려되지않는 목표가 존재합니다."), 
	CAN_NOT_WRITE_BY_DIFFRENT_TARGET_AND_WRITER(404, "R023","개인목표수립의 경우 본인의 목표만 수립가능합니다."), 
	CAN_NOT_UPDATE_BY_DIFFRENT_TARGET_AND_WRITER(404, "R024","개인목표수립의 경우 본인의 목표만 수정가능합니다."),
	CAN_NOT_DELETE_BY_DIFFRENT_TARGET_AND_WRITER(404, "R025","개인목표수립의 경우 본인의 목표만 삭제가능합니다."),
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
