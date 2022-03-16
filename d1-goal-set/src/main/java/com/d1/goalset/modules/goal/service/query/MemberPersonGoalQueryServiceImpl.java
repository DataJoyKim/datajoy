package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.MemberDto.MemberStatusResponse;
import com.d1.goalset.modules.goal.error.MemberErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("MemberPersonGoalQueryService")
@RequiredArgsConstructor
public class MemberPersonGoalQueryServiceImpl implements MemberPersonGoalQueryService {
	
	private final GoalRepository goalRepository;
	private final GoalSettingRepository goalSettingRepository;
	private final UserService userService;
 
	@Override
	public List<GoalResponse> findMembersGoals(String seasonCd, String companyCd, Long userId, Long memberId) {
		User approver = userService.findUser(userId);
		
		User member = userService.findMember(seasonCd, companyCd, approver, memberId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndTargetIdAndGoalType(seasonCd, companyCd, member.getId(), GoalTypeCode.PERSON_GOAL)
														.orElseThrow(() -> new BusinessException(MemberErrorCode.FAULT_APPROVER));
		
		List<Goal> goals = goalRepository.findGoalBy(goalSetting.getId());
		
		return GoalResponse.of(goals);
	}

	@Override
	public GoalResponse findMembersGoal(String seasonCd, String companyCd, Long userId, Long memberId, Long goalId) {
		User approver = userService.findUser(userId);

		User member = userService.findMember(seasonCd, companyCd, approver, memberId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndTargetIdAndGoalType(seasonCd, companyCd, member.getId(), GoalTypeCode.PERSON_GOAL)
														.orElseThrow(() -> new BusinessException(MemberErrorCode.FAULT_APPROVER));
		
		Goal goal = goalRepository.findGoalBy(goalSetting.getId(), goalId)
									.orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_GOAL_OF_MEMBER));
		 
		return GoalResponse.of(goal);
	}

	@Override
	public List<MemberStatusResponse> findMembersGoalsStatus(String seasonCd, String companyCd, Long userId) {
		User approver = userService.findUser(userId);
		
		List<User> members = userService.findMembers(seasonCd, companyCd, approver);
		
		List<GoalSetting> goalSettings = goalSettingRepository.findBySeasonCdAndCompanyCdAndTargetIdInAndGoalType(seasonCd, companyCd, User.createBatchIds(members), GoalTypeCode.PERSON_GOAL);
		 
		return MemberStatusResponse.of(members, goalSettings);
	}
}
