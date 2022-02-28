package com.d1.goalset.modules.goal.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	@Autowired
	GoalSettingValidator goalSettingValidator;

	@Test
	void submitStateValidatorTest() {	
		User approver = getApprover();
		
		User setter = getSetter();
		
		Long targetId = getTargetId();
		
		GoalSetting goalSetting = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		
		List<Goal> goals = new ArrayList<>();
		
		goals.add(Goal.builder()
				.companyCd(companyCd)
				.seasonCd(seasonCd)
				.contents("내용1")
				.targetId(getTargetId())
				.weight(30)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.build());

		goals.add(Goal.builder()
				.companyCd(companyCd)
				.seasonCd(seasonCd)
				.contents("내용2")
				.targetId(getTargetId())
				.weight(70)
				.evalWayCd(EvalWay.QUALITY_EVAL)
				.build());
		
		Throwable exception = assertThrows(BusinessException.class, () -> {goalSetting.submit(goalSettingValidator, goals);});
		
		assertEquals(exception.getMessage(), PersonGoalErrorCode.CAN_NOT_SUBMIT_BY_APPROVAL_STATE);
	}

	private GoalSetting getGoalSetting(User approver, User setter, Long targetId, GoalSettingState goalSettingState) {
		GoalSetting goalSetting = GoalSetting.builder()
				.companyCd(companyCd)
				.seasonCd(seasonCd)
				.approver(approver)
				.goalSettingStatCd(goalSettingState)
				.goalType(GoalTypeCode.PERSON_GOAL)
				.setter(setter)
				.targetId(targetId)
				.build();
		return goalSetting;
	}

	private long getTargetId() {
		return 1L;
	}

	private User getSetter() {
		User setter = User.builder()
				.companyCd(companyCd)
				.seasonCd(seasonCd)
				.id(2L)
				.empNm("김낙영")
				.empNo("NM11704006")
				.isPrimaryAccount(true)
				.isUse(true)
				.build();
		return setter;
	}

	private User getApprover() {
		User approver = User.builder()
					.companyCd(companyCd)
					.seasonCd(seasonCd)
					.id(getTargetId())
					.empNm("김정배")
					.empNo("NM11704006")
					.isPrimaryAccount(true)
					.isUse(true)
					.build();
		return approver;
	}

	@Test
	void test() {
		
		GoalSettingValidator goalSettingValidator = new GoalSettingValidator();
		
		User approver = getApprover();
		
		User setter = getSetter();
		
		Long targetId = getTargetId();
		
		GoalSetting goalSetting = getGoalSetting(approver, setter, targetId, GoalSettingState.APPROVAL);
		
		List<Goal> goals = new ArrayList<>();
		
		goals.add(Goal.builder()
				.companyCd(companyCd)
				.seasonCd(seasonCd)
				.contents("내용1")
				.targetId(getTargetId())
				.weight(30)
				.evalWayCd(EvalWay.QUANT_EVAL)
				.build());

		goals.add(Goal.builder()
				.companyCd(companyCd)
				.seasonCd(seasonCd)
				.contents("내용2")
				.targetId(getTargetId())
				.weight(70)
				.evalWayCd(EvalWay.QUALITY_EVAL)
				.build());
		
		goalSetting.submit(goalSettingValidator, goals);
	}
}
