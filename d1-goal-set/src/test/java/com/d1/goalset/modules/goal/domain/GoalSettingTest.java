package com.d1.goalset.modules.goal.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.User;

class GoalSettingTest {

	private String companyCd = "01";
	private String seasonCd = "202201";
	
	GoalSettingValidator goalSettingValidator = new GoalSettingValidator();

	@DisplayName("제출 시 상태 체크 테스트")
	@Test
	void submitTest() {	
		//Given
		User approver = getApprover(2L);
		
		User setter = getSetter(1L);
		
		Long targetId = getTargetId();
		
		List<Goal> goals = new ArrayList<>();
		
		goals.add(Goal.builder()
				.contents("내용1")
				.goalName("목표명1")
				.weight(30)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build());

		goals.add(Goal.builder()
				.goalName("목표명2")
				.contents("내용2")
				.weight(70)
				.evalWayCd(EvalWay.QUALITY_EVAL)
				.qualityStdS("기준1")
				.qualityStdA("기준2")
				.qualityStdB("기준3")
				.qualityStdC("기준4")
				.qualityStdD("기준5")
				.build());
		
		GoalSetting goalSettingOfApprovalState = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		GoalSetting goalSettingOfSubmitState = getGoalSetting(approver, setter, targetId, GoalSettingState.SUBMIT);
		GoalSetting goalSettingOfCancelState = getGoalSetting(approver, setter, targetId, GoalSettingState.CANCEL);
		GoalSetting goalSettingOfRejectionState = getGoalSetting(approver, setter, targetId, GoalSettingState.REJECTION);
		GoalSetting goalSettingOfSettingState = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		
		// when
		Throwable exceptionOfApprovalState = assertThrows(BusinessException.class, () -> {goalSettingOfApprovalState.submit(goalSettingValidator, goals);});
		Throwable exceptionOfSubmitState = assertThrows(BusinessException.class, () -> {goalSettingOfSubmitState.submit(goalSettingValidator, goals);});
		goalSettingOfCancelState.submit(goalSettingValidator, goals); 
		goalSettingOfRejectionState.submit(goalSettingValidator, goals);
		goalSettingOfSettingState.submit(goalSettingValidator, goals);
		
		// then
		assertEquals(exceptionOfApprovalState.getMessage(), PersonGoalErrorCode.CAN_NOT_SUBMIT_BY_APPROVAL_STATE.getMessage());
		assertEquals(exceptionOfSubmitState.getMessage(), PersonGoalErrorCode.CAN_NOT_SUBMIT_BY_SUBMIT_STATE.getMessage());
		assertEquals(goalSettingOfCancelState.getGoalSettingStateCd(), GoalSettingState.SUBMIT);
		assertEquals(goalSettingOfRejectionState.getGoalSettingStateCd(), GoalSettingState.SUBMIT);
		assertEquals(goalSettingOfSettingState.getGoalSettingStateCd(), GoalSettingState.SUBMIT);
	}
	
	@DisplayName("승인 시 상태 체크 테스트")
	@Test
	void approvalTest() {	
		//Given
		User approver = getApprover(2L);
		
		User setter = getSetter(1L);
		
		Long targetId = getTargetId();
		
		List<Goal> goals = new ArrayList<>();
		
		goals.add(Goal.builder()
				.contents("내용1")
				.goalName("목표명1")
				.weight(30)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build());

		goals.add(Goal.builder()
				.goalName("목표명2")
				.contents("내용2")
				.weight(70)
				.evalWayCd(EvalWay.QUALITY_EVAL)
				.qualityStdS("기준1")
				.qualityStdA("기준2")
				.qualityStdB("기준3")
				.qualityStdC("기준4")
				.qualityStdD("기준5")
				.build());
		
		GoalSetting goalSettingOfApprovalState = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		GoalSetting goalSettingOfSubmitState = getGoalSetting(approver, setter, targetId, GoalSettingState.SUBMIT);
		GoalSetting goalSettingOfCancelState = getGoalSetting(approver, setter, targetId, GoalSettingState.CANCEL);
		GoalSetting goalSettingOfRejectionState = getGoalSetting(approver, setter, targetId, GoalSettingState.REJECTION);
		GoalSetting goalSettingOfSettingState = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		
		// when
		Throwable exceptionOfApprovalState = assertThrows(BusinessException.class, () -> {goalSettingOfApprovalState.approve(goalSettingValidator, goals);});
		goalSettingOfSubmitState.approve(goalSettingValidator, goals);
		Throwable exceptionOfCanceltState = assertThrows(BusinessException.class, () -> {goalSettingOfCancelState.approve(goalSettingValidator, goals);});
		Throwable exceptionOfRejectiontState = assertThrows(BusinessException.class, () -> {goalSettingOfRejectionState.approve(goalSettingValidator, goals);});
		Throwable exceptionOfSettingState = assertThrows(BusinessException.class, () -> {goalSettingOfSettingState.approve(goalSettingValidator, goals);});
		
		// then
		assertEquals(exceptionOfApprovalState.getMessage(), PersonGoalErrorCode.CAN_NOT_APPROVE_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(goalSettingOfSubmitState.getGoalSettingStateCd(), GoalSettingState.APPROVAL);
		assertEquals(exceptionOfCanceltState.getMessage(), PersonGoalErrorCode.CAN_NOT_APPROVE_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(exceptionOfRejectiontState.getMessage(), PersonGoalErrorCode.CAN_NOT_APPROVE_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(exceptionOfSettingState.getMessage(), PersonGoalErrorCode.CAN_NOT_APPROVE_BY_NOT_SUBMIT_STATE.getMessage());
	}

	@DisplayName("반려 시 상태 체크 테스트")
	@Test
	void rejectTest() {	
		//Given
		User approver = getApprover(2L);
		
		User setter = getSetter(1L);
		
		Long targetId = getTargetId();
		
		List<Goal> goals = new ArrayList<>();
		
		goals.add(Goal.builder()
				.contents("내용1")
				.goalName("목표명1")
				.weight(30)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build());

		goals.add(Goal.builder()
				.goalName("목표명2")
				.contents("내용2")
				.weight(70)
				.evalWayCd(EvalWay.QUALITY_EVAL)
				.qualityStdS("기준1")
				.qualityStdA("기준2")
				.qualityStdB("기준3")
				.qualityStdC("기준4")
				.qualityStdD("기준5")
				.build());
		
		GoalSetting goalSettingOfApprovalState = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		GoalSetting goalSettingOfSubmitState = getGoalSetting(approver, setter, targetId, GoalSettingState.SUBMIT);
		GoalSetting goalSettingOfCancelState = getGoalSetting(approver, setter, targetId, GoalSettingState.CANCEL);
		GoalSetting goalSettingOfRejectionState = getGoalSetting(approver, setter, targetId, GoalSettingState.REJECTION);
		GoalSetting goalSettingOfSettingState = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		
		// when
		Throwable exceptionOfApprovalState = assertThrows(BusinessException.class, () -> {goalSettingOfApprovalState.reject(goalSettingValidator, goals);});
		goalSettingOfSubmitState.reject(goalSettingValidator, goals);
		Throwable exceptionOfCanceltState = assertThrows(BusinessException.class, () -> {goalSettingOfCancelState.reject(goalSettingValidator, goals);});
		Throwable exceptionOfRejectiontState = assertThrows(BusinessException.class, () -> {goalSettingOfRejectionState.reject(goalSettingValidator, goals);});
		Throwable exceptionOfSettingState = assertThrows(BusinessException.class, () -> {goalSettingOfSettingState.reject(goalSettingValidator, goals);});
		
		// then
		assertEquals(exceptionOfApprovalState.getMessage(), PersonGoalErrorCode.CAN_NOT_REJECT_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(goalSettingOfSubmitState.getGoalSettingStateCd(), GoalSettingState.REJECTION);
		assertEquals(exceptionOfCanceltState.getMessage(), PersonGoalErrorCode.CAN_NOT_REJECT_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(exceptionOfRejectiontState.getMessage(), PersonGoalErrorCode.CAN_NOT_REJECT_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(exceptionOfSettingState.getMessage(), PersonGoalErrorCode.CAN_NOT_REJECT_BY_NOT_SUBMIT_STATE.getMessage());
	}

	@DisplayName("회수 시 상태 체크 테스트")
	@Test
	void cancelTest() {	
		//Given
		User approver = getApprover(2L);
		
		User setter = getSetter(1L);
		
		Long targetId = getTargetId();
		
		List<Goal> goals = new ArrayList<>();
		
		goals.add(Goal.builder()
				.contents("내용1")
				.goalName("목표명1")
				.weight(30)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build());

		goals.add(Goal.builder()
				.goalName("목표명2")
				.contents("내용2")
				.weight(70)
				.evalWayCd(EvalWay.QUALITY_EVAL)
				.qualityStdS("기준1")
				.qualityStdA("기준2")
				.qualityStdB("기준3")
				.qualityStdC("기준4")
				.qualityStdD("기준5")
				.build());
		
		GoalSetting goalSettingOfApprovalState = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		GoalSetting goalSettingOfSubmitState = getGoalSetting(approver, setter, targetId, GoalSettingState.SUBMIT);
		GoalSetting goalSettingOfCancelState = getGoalSetting(approver, setter, targetId, GoalSettingState.CANCEL);
		GoalSetting goalSettingOfRejectionState = getGoalSetting(approver, setter, targetId, GoalSettingState.REJECTION);
		GoalSetting goalSettingOfSettingState = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		
		// when
		Throwable exceptionOfApprovalState = assertThrows(BusinessException.class, () -> {goalSettingOfApprovalState.cancel(goalSettingValidator, goals);});
		goalSettingOfSubmitState.cancel(goalSettingValidator, goals);
		Throwable exceptionOfCanceltState = assertThrows(BusinessException.class, () -> {goalSettingOfCancelState.cancel(goalSettingValidator, goals);});
		Throwable exceptionOfRejectiontState = assertThrows(BusinessException.class, () -> {goalSettingOfRejectionState.cancel(goalSettingValidator, goals);});
		Throwable exceptionOfSettingState = assertThrows(BusinessException.class, () -> {goalSettingOfSettingState.cancel(goalSettingValidator, goals);});
		
		// then
		assertEquals(exceptionOfApprovalState.getMessage(), PersonGoalErrorCode.CAN_NOT_COLLECT_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(goalSettingOfSubmitState.getGoalSettingStateCd(), GoalSettingState.CANCEL);
		assertEquals(exceptionOfCanceltState.getMessage(), PersonGoalErrorCode.CAN_NOT_COLLECT_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(exceptionOfRejectiontState.getMessage(), PersonGoalErrorCode.CAN_NOT_COLLECT_BY_NOT_SUBMIT_STATE.getMessage());
		assertEquals(exceptionOfSettingState.getMessage(), PersonGoalErrorCode.CAN_NOT_COLLECT_BY_NOT_SUBMIT_STATE.getMessage());
	}

	@DisplayName("목표 작성 시 상태 체크 테스트")
	@Test
	void writeGoalTest() {	
		//Given
		User approver = getApprover(2L);
		
		User setter = getSetter(1L);
		
		Long targetId = getTargetId();
		
		Goal goal = Goal.builder()
				.id(1L)
				.contents("내용1")
				.goalName("목표명1")
				.weight(30)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build();
		
		GoalSetting goalSettingOfApprovalState = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		GoalSetting goalSettingOfSubmitState = getGoalSetting(approver, setter, targetId, GoalSettingState.SUBMIT);
		GoalSetting goalSettingOfCancelState = getGoalSetting(approver, setter, targetId, GoalSettingState.CANCEL);
		GoalSetting goalSettingOfRejectionState = getGoalSetting(approver, setter, targetId, GoalSettingState.REJECTION);
		GoalSetting goalSettingOfSettingState = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		
		// when
		Throwable exceptionOfApprovalState = assertThrows(BusinessException.class, () -> {goalSettingOfApprovalState.writeGoal(goalSettingValidator, setter, goal);});
		Throwable exceptionOfSubmitState = assertThrows(BusinessException.class, () -> {goalSettingOfSubmitState.writeGoal(goalSettingValidator, setter, goal);});
		goalSettingOfCancelState.writeGoal(goalSettingValidator, setter, goal);
		goalSettingOfRejectionState.writeGoal(goalSettingValidator, setter, goal);
		goalSettingOfSettingState.writeGoal(goalSettingValidator, setter, goal);
		
		// then
		assertEquals(exceptionOfApprovalState.getMessage(), PersonGoalErrorCode.CAN_NOT_WRITE_BY_APPROVAL_STATE.getMessage());
		assertEquals(exceptionOfSubmitState.getMessage(), PersonGoalErrorCode.CAN_NOT_WRITE_BY_SUBMIT_STATE.getMessage());
		assertEquals(goalSettingOfCancelState.getGoalSettingStateCd(), GoalSettingState.SETTING);
		assertEquals(goalSettingOfRejectionState.getGoalSettingStateCd(), GoalSettingState.SETTING);
		assertEquals(goalSettingOfSettingState.getGoalSettingStateCd(), GoalSettingState.SETTING);
	}

	@DisplayName("목표 작성 시 입력 데이터 체크 테스트")
	@Test
	void writeGoalDataTest() {	
		//Given
		User approver = getApprover(2L);
		
		User setter = getSetter(1L);
		
		Long targetId = getTargetId();
		
		Goal goal = Goal.builder()
				.id(1L)
				.contents("내용1")
				.goalName("목표명1")
				.weight(30)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build();
		
		Goal goalWeightMaxException = Goal.builder()
				.id(2L)
				.contents("내용1")
				.goalName("목표명1")
				.weight(101)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build();
		
		Goal goalWeightMinException = Goal.builder()
				.id(3L)
				.contents("내용1")
				.goalName("목표명1")
				.weight(0)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.quantStdMax("기준1")
				.quantStdGoal("기준2")
				.quantStdMin("기준3")
				.build();
		
		GoalSetting goalSettingOfSettingState = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		GoalSetting goalSettingOfWeightMaxException = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		GoalSetting goalSettingOfWeightMinException = getGoalSetting(approver, setter, targetId, GoalSettingState.SETTING);
		
		// when
		goalSettingOfSettingState.writeGoal(goalSettingValidator, setter, goal);
		Throwable exceptionOfWeightMaxException = assertThrows(BusinessException.class, () -> {goalSettingOfWeightMaxException.writeGoal(goalSettingValidator, setter, goalWeightMaxException);});
		Throwable exceptionOfWeightMinException = assertThrows(BusinessException.class, () -> {goalSettingOfWeightMinException.writeGoal(goalSettingValidator, setter, goalWeightMinException);});
		
		// then
		assertEquals(exceptionOfWeightMaxException.getMessage(), PersonGoalErrorCode.NOT_RANGE_WEIGHT.getMessage());
		assertEquals(exceptionOfWeightMinException.getMessage(), PersonGoalErrorCode.NOT_RANGE_WEIGHT.getMessage());
		assertEquals(goalSettingOfSettingState.getGoals().size(), 1);
		assertEquals(goalSettingOfSettingState.getGoals().get(0).getId(), 1L);
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
