package com.d1.goalset.modules.goal.validator;

import org.springframework.stereotype.Component;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.user.domain.User;

@Component
public class GoalSettingValidator {

	public void validateCreateGoal(GoalSetting goalSetting, User writer, GoalWritingRequest params) {
		if(writer == null) {
			throw new BusinessException(PersonGoalErrorCode.NULL_WRITER);
		}
		
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_WRITE_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_WRITE_BY_SUBMIT_STATE);
		}
		
		if(params.getWeight() <= 0 || params.getWeight() > 100) {
			throw new BusinessException(PersonGoalErrorCode.NOT_RANGE_WEIGHT);
		}
	}

	public void validateUpdateGoal(Goal goal, GoalSetting goalSetting, User setter, GoalWritingRequest params) {
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_UPDATE_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_UPDATE_BY_SUBMIT_STATE);
		}

		if(GoalWritingState.DELETE.equals(goal.getGoalWritingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_UPDATE_BY_DELETE_STATE);
		}
		
	}

	public void validateDeleteGoal(Goal goal, GoalSetting goalSetting, User setter) {		
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_DELETE_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_DELETE_BY_SUBMIT_STATE);
		}
		
		if(GoalWritingState.DELETE.equals(goal.getGoalWritingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_DELETE_BY_DELETE_STATE);
		}
	}

	public void validateSubmit(GoalSetting goalSetting) {
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_SUBMIT_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_SUBMIT_BY_SUBMIT_STATE);	
		}
		
		if(goalSetting.getSumWeight() != 100) {
			throw new BusinessException(PersonGoalErrorCode.NOT_FAULT_VALUE_BY_SUM_WEIGHT);
		}
	}

}
