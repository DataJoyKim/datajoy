package com.d1.goalset.modules.goal.validator;

import org.springframework.stereotype.Component;

import com.d1.goalset.modules.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.user.domain.GoalSetter;

@Component
public class GoalSettingValidator {

	public void validateWrite(GoalSetting goalSetting, GoalSetter writer, GoalWritingRequest params) {
		if(writer == null) {
			throw new BusinessException(PersonGoalErrorCode.NULL_WRITER);
		}
		
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_WRITE_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStatCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_WRITE_BY_SUBMIT_STATE);
		}
	}

}
