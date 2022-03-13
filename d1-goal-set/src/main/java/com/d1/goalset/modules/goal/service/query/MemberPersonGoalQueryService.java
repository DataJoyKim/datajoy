package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.MemberDto.MemberStatusResponse;

public interface MemberPersonGoalQueryService {

	/**
	 * 개인목표 내역 조회
	 * @param seasonCd
	 * @param companyCd
	 * @param approverId
	 * @param memberId
	 * @return
	 */
	List<GoalResponse> findMembersGoals(String seasonCd, String companyCd, Long userId, Long memberId);
	
	/**
	 * 개인목표 조회
	 * @param seasonCd
	 * @param companyCd
	 * @param approverId
	 * @param memberId
	 * @param goalId
	 * @return
	 */
	GoalResponse findMembersGoal(String seasonCd, String companyCd, Long userId, Long memberId, Long goalId);

	/**
	 * 개인목표상태 조회
	 * @param seasonCd 시즌코드
	 * @param companyCd 회사코드
	 * @param userId 사용자 ID
	 * @return
	 */
	List<MemberStatusResponse> findMembersGoalsStatus(String seasonCd, String companyCd, Long userId);

}
