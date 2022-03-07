package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;

public interface MemberQueryService {

	List<GoalResponse> findMembersGoals(String seasonCd, String companyCd, Long approverId, Long memberId);
	
	GoalResponse findMembersGoal(String seasonCd, String companyCd, Long approverId, Long memberId, Long goalId);

}
