package com.d1.goalset.modules.goal.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.User;

class GoalTest {

	private String companyCd = "01";
	private String seasonCd = "202201";
	
	GoalSettingValidator goalSettingValidator = new GoalSettingValidator();

	@DisplayName("목표 수정 테스트")
	@Test
	void updateGoalTest() {
		//Given
		User approver = getApprover(2L);
		
		User setter = getSetter(1L);
		
		Long targetId = getTargetId();
		
		Goal goalfApprovalState  = createGoal(GoalWritingState.SAVE, 30);
		Goal goalfSubmitState = createGoal(GoalWritingState.SAVE, 30);
		Goal goalfCancelState = createGoal(GoalWritingState.SAVE, 30);
		Goal goalfRejectionState = createGoal(GoalWritingState.SAVE, 30);
		Goal goalfSettingState = createGoal(GoalWritingState.SAVE, 30);
		Goal deletedGoal = createGoal(GoalWritingState.DELETE, 30);
		
		String updateContent = "update";
		Integer updateWeight = 80;
		
		GoalWritingRequest params = createGoalWritingRequest(updateContent, updateWeight);
		
		GoalWritingRequest paramsOfMinWeightException = createGoalWritingRequest(updateContent, 0);
		
		GoalWritingRequest paramsOfMaxWeightException = createGoalWritingRequest(updateContent, 101);

		GoalSetting goalSettingOfApprovalState = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		GoalSetting goalSettingOfSubmitState = getGoalSetting(approver, setter, targetId, GoalSettingState.SUBMIT);
		GoalSetting goalSettingOfCancelState = getGoalSetting(approver, setter, targetId, GoalSettingState.CANCEL);
		GoalSetting goalSettingOfRejectionState = getGoalSetting(approver, setter, targetId, GoalSettingState.REJECTION);
		GoalSetting goalSettingOfSettingState = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		
		// when
		Throwable exceptionOfApprovalState = assertThrows(BusinessException.class, () -> {goalfApprovalState.update(goalSettingValidator, goalSettingOfApprovalState, setter, params );});
		Throwable exceptionOfSubmitState = assertThrows(BusinessException.class, () -> {goalfSubmitState.update(goalSettingValidator, goalSettingOfSubmitState, setter, params );});
		goalfCancelState.update(goalSettingValidator, goalSettingOfCancelState, setter, params );
		goalfRejectionState.update(goalSettingValidator, goalSettingOfRejectionState, setter, params );
		goalfSettingState.update(goalSettingValidator, goalSettingOfSettingState, setter, params );
		Throwable exceptionOfMinWeightException = assertThrows(BusinessException.class, () -> {goalfSettingState.update(goalSettingValidator, goalSettingOfSettingState, setter, paramsOfMinWeightException );});
		Throwable exceptionOfMaxWeightException = assertThrows(BusinessException.class, () -> {goalfSettingState.update(goalSettingValidator, goalSettingOfSettingState, setter, paramsOfMaxWeightException );});
		Throwable exceptionOfDeletedGoal = assertThrows(BusinessException.class, () -> {deletedGoal.update(goalSettingValidator, goalSettingOfSettingState, setter, params );});
		
		// then
		assertEquals(exceptionOfApprovalState.getMessage(), PersonGoalErrorCode.CAN_NOT_UPDATE_BY_APPROVAL_STATE.getMessage());
		assertEquals(exceptionOfSubmitState.getMessage(), PersonGoalErrorCode.CAN_NOT_UPDATE_BY_SUBMIT_STATE.getMessage());
		assertEquals(goalfCancelState.getGoalName(), updateContent);
		assertEquals(goalfRejectionState.getGoalName(), updateContent);
		assertEquals(goalfSettingState.getGoalName(), updateContent);
		assertEquals(goalfSettingState.getContents(), updateContent);
		assertEquals(goalfSettingState.getWeight(), updateWeight);
		assertEquals(goalfSettingState.getEvalWayCd(), EvalWay.QUALITY_EVAL);
		assertEquals(goalfSettingState.getQualityStdS(), updateContent);
		assertEquals(goalfSettingState.getQualityStdA(), updateContent);
		assertEquals(goalfSettingState.getQualityStdB(), updateContent);
		assertEquals(goalfSettingState.getQualityStdC(), updateContent);
		assertEquals(goalfSettingState.getQualityStdD(), updateContent);
		assertEquals(exceptionOfMinWeightException.getMessage(), PersonGoalErrorCode.NOT_RANGE_WEIGHT.getMessage());
		assertEquals(exceptionOfMaxWeightException.getMessage(), PersonGoalErrorCode.NOT_RANGE_WEIGHT.getMessage());
		assertEquals(exceptionOfDeletedGoal.getMessage(), PersonGoalErrorCode.CAN_NOT_UPDATE_BY_DELETE_STATE.getMessage());
	}

	private GoalWritingRequest createGoalWritingRequest(String updateContent, Integer updateWeight) {
		return GoalWritingRequest.builder()
									.id(1L)
									.goalName(updateContent)
									.contents(updateContent)
									.weight(updateWeight)
									.evalWayCd(EvalWay.QUALITY_EVAL)
									.qualityStdS(updateContent)
									.qualityStdA(updateContent)
									.qualityStdB(updateContent)
									.qualityStdC(updateContent)
									.qualityStdD(updateContent)
									.build();
	}
	
	@DisplayName("목표 삭제 테스트")
	@Test
	void deleteGoalTest() {
		//Given
		User approver = getApprover(2L);
		
		User setter = getSetter(1L);
		
		Long targetId = getTargetId();
		
		Goal goalfApprovalState  = createGoal(GoalWritingState.SAVE, 30);
		Goal goalfSubmitState = createGoal(GoalWritingState.SAVE, 30);
		Goal goalfCancelState = createGoal(GoalWritingState.SAVE, 30);
		Goal goalfRejectionState = createGoal(GoalWritingState.SAVE, 30);
		Goal goalfSettingState = createGoal(GoalWritingState.SAVE, 30);
		Goal deletedGoal = createGoal(GoalWritingState.DELETE, 30);

		GoalSetting goalSettingOfApprovalState = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		GoalSetting goalSettingOfSubmitState = getGoalSetting(approver, setter, targetId, GoalSettingState.SUBMIT);
		GoalSetting goalSettingOfCancelState = getGoalSetting(approver, setter, targetId, GoalSettingState.CANCEL);
		GoalSetting goalSettingOfRejectionState = getGoalSetting(approver, setter, targetId, GoalSettingState.REJECTION);
		GoalSetting goalSettingOfSettingState = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		
		// when
		Throwable exceptionOfApprovalState = assertThrows(BusinessException.class, () -> {goalfApprovalState.delete(goalSettingValidator, goalSettingOfApprovalState, setter);});
		Throwable exceptionOfSubmitState = assertThrows(BusinessException.class, () -> {goalfSubmitState.delete(goalSettingValidator, goalSettingOfSubmitState, setter);});
		goalfCancelState.delete(goalSettingValidator, goalSettingOfCancelState, setter);
		goalfRejectionState.delete(goalSettingValidator, goalSettingOfRejectionState, setter);
		goalfSettingState.delete(goalSettingValidator, goalSettingOfSettingState, setter);
		Throwable exceptionOfDeletedGoal = assertThrows(BusinessException.class, () -> {deletedGoal.delete(goalSettingValidator, goalSettingOfSettingState, setter);});
		
		// then
		assertEquals(exceptionOfApprovalState.getMessage(), PersonGoalErrorCode.CAN_NOT_DELETE_BY_APPROVAL_STATE.getMessage());
		assertEquals(exceptionOfSubmitState.getMessage(), PersonGoalErrorCode.CAN_NOT_DELETE_BY_SUBMIT_STATE.getMessage());
		assertEquals(goalfCancelState.getGoalWritingStateCd(), GoalWritingState.DELETE);
		assertEquals(goalfRejectionState.getGoalWritingStateCd(), GoalWritingState.DELETE);
		assertEquals(goalfSettingState.getGoalWritingStateCd(), GoalWritingState.DELETE);
		assertEquals(exceptionOfDeletedGoal.getMessage(), PersonGoalErrorCode.CAN_NOT_DELETE_BY_DELETE_STATE.getMessage());
	}

	private Goal createGoal(GoalWritingState state, Integer weight) {
		return Goal.builder()
				.id(1L)
				.goalWritingStateCd(state)
				.contents("내용1")
				.goalName("목표명1")
				.weight(weight)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build();
	}

	private GoalSetting getGoalSetting(User approver, User setter, Long targetId, GoalSettingState goalSettingState) {
		return GoalSetting.builder()
				.companyCd(companyCd)
				.seasonCd(seasonCd)
				.goalSettingStateCd(goalSettingState)
				.goalType(GoalTypeCode.PERSON_GOAL)
				.targetId(targetId)
				.build();
	}

	private long getTargetId() {
		return 1L;
	}

	private User getSetter(Long id) {
		return User.builder()
				.companyCd(companyCd)
				.seasonCd(seasonCd)
				.id(id)
				.empNm("김낙영")
				.empNo("NM11704006")
				.isPrimaryAccount(true)
				.isUse(true)
				.build();
	}

	private User getApprover(Long id) {
		return User.builder()
					.companyCd(companyCd)
					.seasonCd(seasonCd)
					.id(id)
					.empNm("김정배")
					.empNo("NM11704006")
					.isPrimaryAccount(true)
					.isUse(true)
					.build();
	}
}
