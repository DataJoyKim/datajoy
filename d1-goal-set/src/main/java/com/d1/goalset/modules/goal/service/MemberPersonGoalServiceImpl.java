package com.d1.goalset.modules.goal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.error.MemberErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service("MemberPersonGoalService")
@RequiredArgsConstructor
public class MemberPersonGoalServiceImpl implements MemberPersonGoalService {
	
	private final GoalSettingRepository goalSettingRepository;
	private final GoalRepository goalRepository;
	private final GoalSettingValidator goalSettingValidator;
	private final UserService userService;

	@Transactional
	@Override
	public void approve(String seasonCd, String companyCd, Long userId, Long memberId) {
		User approver = userService.findUser(userId);
		
		User member = userService.findMember(seasonCd, companyCd, approver, memberId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndTargetIdAndGoalType(seasonCd, companyCd, member.getId(), GoalTypeCode.PERSON_GOAL)
														.orElseThrow(() -> new BusinessException(MemberErrorCode.FAULT_APPROVER));
		
		List<Goal> goals = goalRepository.findGoalBy(goalSetting.getId());
		
		goalSetting.approve(goalSettingValidator, goals);
	}

}
