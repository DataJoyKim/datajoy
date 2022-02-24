package com.d1.goalset.modules.goal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.error.MemberErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service("MemberService")
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final GoalSettingRepository goalSettingRepository;
	private final GoalRepository goalRepository;
	private final GoalSettingValidator goalSettingValidator;
	private final UserRepository userRepository;
	
	@Transactional
	@Override
	public void approve(String seasonCd, String companyCd, User approver, Long memberId) {
		
		User member = userRepository.findById(memberId).orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_MEMBER));
		
		List<GoalSetting> goalSettingOfMembers = goalSettingRepository.findBySeasonCdAndCompanyCdAndApproverAndSetter(seasonCd, companyCd, approver, member);
		if(goalSettingOfMembers.size() <= 0) {
			throw new BusinessException(MemberErrorCode.FAULT_APPROVER);
		}
		
		GoalSetting goalSetting = goalSettingOfMembers.get(0);
		
		List<Goal> goals = goalRepository.findGoalBy(goalSetting.getTargetId());
		
		goalSetting.approve(goalSettingValidator, goals);
	}

}
