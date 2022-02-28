package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;

public interface MemberQueryService {

	List<UserResponse> findMembers(String seasonCd, String companyCd, Long approverId, GoalTypeCode goalTypeCode);

	UserResponse findMember(String seasonCd, String companyCd, Long approverId, GoalTypeCode goalTypeCode, Long memberId);

	List<GoalResponse> findMembersGoals(String seasonCd, String companyCd, Long approverId, Long memberId);
	
	GoalResponse findMembersGoal(String seasonCd, String companyCd, Long approverId, Long memberId, Long goalId);

}
