package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.MemberDto.MemberStatusResponse;

public interface MemberQueryService {

	List<GoalResponse> findMembersGoals(String seasonCd, String companyCd, Long approverId, Long memberId);
	
	GoalResponse findMembersGoal(String seasonCd, String companyCd, Long approverId, Long memberId, Long goalId);

	/**
	 * 개인목표상태 조회
	 * @param seasonCd 시즌코드
	 * @param companyCd 회사코드
	 * @param userId 사용자 ID
	 * @return
	 */
	List<MemberStatusResponse> findMembersPersonGoalsStatus(String seasonCd, String companyCd, Long userId);
	
	/**
	 * 조직목표상태 조회
	 * @param seasonCd 시즌코드
	 * @param companyCd 회사코드
	 * @param userId 사용자 ID
	 * @return
	 */
	List<MemberStatusResponse> findMembersOrgGoalsStatus(String seasonCd, String companyCd, Long userId);

}
