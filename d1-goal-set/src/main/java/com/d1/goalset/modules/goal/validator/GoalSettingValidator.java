package com.d1.goalset.modules.goal.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.EvalWay;
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
		
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_WRITE_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_WRITE_BY_SUBMIT_STATE);
		}
		
		if(params.getWeight() <= 0 || params.getWeight() > 100) {
			throw new BusinessException(PersonGoalErrorCode.NOT_RANGE_WEIGHT);
		}
	}

	public void validateUpdateGoal(Goal goal, GoalSetting goalSetting, User setter, GoalWritingRequest params) {
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_UPDATE_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_UPDATE_BY_SUBMIT_STATE);
		}

		if(GoalWritingState.DELETE.equals(goal.getGoalWritingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_UPDATE_BY_DELETE_STATE);
		}
		
		if(params.getWeight() <= 0 || params.getWeight() > 100) {
			throw new BusinessException(PersonGoalErrorCode.NOT_RANGE_WEIGHT);
		}
	}

	public void validateDeleteGoal(Goal goal, GoalSetting goalSetting, User setter) {		
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_DELETE_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_DELETE_BY_SUBMIT_STATE);
		}
		
		if(GoalWritingState.DELETE.equals(goal.getGoalWritingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_DELETE_BY_DELETE_STATE);
		}
	}

	public void validateSubmit(GoalSetting goalSetting, List<Goal> goals) {
		if(GoalSettingState.APPROVAL.equals(goalSetting.getGoalSettingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_SUBMIT_BY_APPROVAL_STATE);
		}
		
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStateCd())) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_SUBMIT_BY_SUBMIT_STATE);	
		}
		
		if(goalSetting.getSumWeight() != 100) {
			throw new BusinessException(PersonGoalErrorCode.NOT_FAULT_VALUE_BY_SUM_WEIGHT);
		}
		
		for(Goal goal : goals) {
			if(goal.getGoalName() == null || goal.getGoalName().isEmpty()) {
				throw new BusinessException(PersonGoalErrorCode.NULL_GOAL_NAME);
			}
			
			if(goal.getEvalWayCd() == null) {
				throw new BusinessException(PersonGoalErrorCode.NULL_EVAL_WAY_CD);
			}
			
			if(EvalWay.QUALITY_EVAL.equals(goal.getEvalWayCd())) {
				if(goal.getQualityStdS() == null || goal.getQualityStdS().isEmpty() 
				|| goal.getQualityStdA() == null || goal.getQualityStdA().isEmpty()
				|| goal.getQualityStdB() == null || goal.getQualityStdB().isEmpty()
				|| goal.getQualityStdC() == null || goal.getQualityStdC().isEmpty()
				|| goal.getQualityStdD() == null || goal.getQualityStdD().isEmpty()) {
					throw new BusinessException(PersonGoalErrorCode.NULL_QUALITY_STD);
				}
			}
			else if(EvalWay.QUANT_EVAL.equals(goal.getEvalWayCd())) {
				if(goal.getQuantStdMax() == null || goal.getQuantStdMax().isEmpty()
				|| goal.getQuantStdGoal() == null || goal.getQuantStdGoal().isEmpty()
				|| goal.getQuantStdMin() == null || goal.getQuantStdMin().isEmpty()) {
					throw new BusinessException(PersonGoalErrorCode.NULL_QUANT_STD);
				}
			}
			
			if(goal.getContents() == null || goal.getContents().isEmpty()) {
				throw new BusinessException(PersonGoalErrorCode.NULL_CONTENTS);
			}
		}
	}

	public void validateApproval(GoalSetting goalSetting) {
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStateCd()) == false) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_APPROVE_BY_NOT_SUBMIT_STATE);	
		}
	}

	public void validateRejection(GoalSetting goalSetting) {
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStateCd()) == false) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_REJECT_BY_NOT_SUBMIT_STATE);	
		}
	}

	public void validateCancel(GoalSetting goalSetting) {
		if(GoalSettingState.SUBMIT.equals(goalSetting.getGoalSettingStateCd()) == false) {
			throw new BusinessException(PersonGoalErrorCode.CAN_NOT_COLLECT_BY_NOT_SUBMIT_STATE);	
		}
	}

}
