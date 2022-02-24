package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.error.MemberErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;
import com.d1.goalset.modules.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("MemberQueryService")
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
	
	private final GoalRepository goalRepository;
	private final GoalSettingRepository goalSettingRepository;
	private final UserRepository userRepository;
	
	@Override
	public List<UserResponse> findMembers(String seasonCd, String companyCd, User approver, GoalTypeCode goalTypeCode) {
		
		List<GoalSetting> goalSettingOfMembers = goalSettingRepository.findBySeasonCdAndCompanyCdAndApprover(seasonCd, companyCd, approver);
		
		List<Long> batchSetterIds = GoalSetting.createBatchSetterIds(goalSettingOfMembers);
		
		List<User> members = userRepository.findBySeasonCdAndCompanyCdAndIdIn(seasonCd, companyCd, batchSetterIds);
		
		return UserResponse.of(members);
	}

	@Override
	public UserResponse findMember(String seasonCd, String companyCd, User approver, GoalTypeCode goalTypeCode, Long memberId) {
		
		User member = userRepository.findById(memberId).orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_MEMBER));
		
		List<GoalSetting> goalSettingOfMembers = goalSettingRepository.findBySeasonCdAndCompanyCdAndApproverAndSetter(seasonCd, companyCd, approver, member);
		if(goalSettingOfMembers.size() <= 0) {
			throw new BusinessException(MemberErrorCode.FAULT_APPROVER);
		}
		
		return UserResponse.of(member);
	}

	@Override
	public List<GoalResponse> findMembersGoals(String seasonCd, String companyCd, User approver, Long memberId) {
		
		User member = userRepository.findById(memberId).orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_MEMBER));
		
		List<GoalSetting> goalSettingOfMembers = goalSettingRepository.findBySeasonCdAndCompanyCdAndApproverAndSetter(seasonCd, companyCd, approver, member);
		if(goalSettingOfMembers.size() <= 0) {
			throw new BusinessException(MemberErrorCode.FAULT_APPROVER);
		}
		
		GoalSetting goalSetting = goalSettingOfMembers.get(0);
		
		List<Goal> goals = goalRepository.findGoalBy(goalSetting.getTargetId());
		
		return GoalResponse.of(goals);
	}

	@Override
	public GoalResponse findMembersGoal(String seasonCd, String companyCd, User approver, Long memberId, Long goalId) {
		
		User member = userRepository.findById(memberId).orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_MEMBER));
		
		List<GoalSetting> goalSettingOfMembers = goalSettingRepository.findBySeasonCdAndCompanyCdAndApproverAndSetter(seasonCd, companyCd, approver, member);
		if(goalSettingOfMembers.size() <= 0) {
			throw new BusinessException(MemberErrorCode.FAULT_APPROVER);
		}
		
		GoalSetting goalSetting = goalSettingOfMembers.get(0);
		
		Goal goal = goalRepository.findGoalBy(goalSetting.getTargetId(), goalId).orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_GOAL_OF_MEMBER));
		 
		return GoalResponse.of(goal);
	}

}
