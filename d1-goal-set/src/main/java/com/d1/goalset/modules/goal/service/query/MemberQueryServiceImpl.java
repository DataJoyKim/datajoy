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
import com.d1.goalset.modules.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("MemberQueryService")
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
	
	private final GoalRepository goalRepository;
	private final GoalSettingRepository goalSettingRepository;
	private final UserService userService;
	
	@Override
	public List<UserResponse> findMembers(String seasonCd, String companyCd, Long approverId, GoalTypeCode goalTypeCode) {
		User approver = userService.findUser(approverId);
		
		return UserResponse.of(userService.findMembers(seasonCd, companyCd, approver));
	}

	@Override
	public UserResponse findMember(String seasonCd, String companyCd, Long approverId, GoalTypeCode goalTypeCode, Long memberId) {
		User approver = userService.findUser(approverId);

		return UserResponse.of(userService.findMember(seasonCd, companyCd, approver, memberId));
	}

	@Override
	public List<GoalResponse> findMembersGoals(String seasonCd, String companyCd, Long approverId, Long memberId) {
		User approver = userService.findUser(approverId);
		
		User member = userService.findMember(seasonCd, companyCd, approver, memberId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndTargetId(seasonCd, companyCd, member.getId())
														.orElseThrow(() -> new BusinessException(MemberErrorCode.FAULT_APPROVER));
		
		//TODO 조직목표 데이터 add All 필요
		
		List<Goal> goals = goalRepository.findGoalBy(seasonCd, companyCd, goalSetting.getTargetId());
		
		return GoalResponse.of(goals);
	}

	@Override
	public GoalResponse findMembersGoal(String seasonCd, String companyCd, Long approverId, Long memberId, Long goalId) {
		User approver = userService.findUser(approverId);

		User member = userService.findMember(seasonCd, companyCd, approver, memberId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndTargetId(seasonCd, companyCd, member.getId())
														.orElseThrow(() -> new BusinessException(MemberErrorCode.FAULT_APPROVER));
		
		Goal goal = goalRepository.findGoalBy(seasonCd, companyCd, goalSetting.getTargetId(), goalId)
									.orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_GOAL_OF_MEMBER));
		 
		return GoalResponse.of(goal);
	}

}
