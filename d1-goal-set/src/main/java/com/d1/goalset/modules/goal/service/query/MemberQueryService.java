package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.MemberDto.MemberStatusResponse;

public interface MemberQueryService {

	/**
	 * 개인목표 내역 조회
	 * @param seasonCd
	 * @param companyCd
	 * @param approverId
	 * @param memberId
	 * @return
	 */
	List<GoalResponse> findMembersPersonGoals(String seasonCd, String companyCd, Long userId, Long memberId);
	
	/**
	 * 조직목표내역 조회
	 * @param seasonCd
	 * @param companyCd
	 * @param approverId
	 * @param memberId
	 * @return
	 */
	List<GoalResponse> findMembersOrgGoals(String seasonCd, String companyCd, Long userId, Long memberId);
	
	/**
	 * 개인목표 조회
	 * @param seasonCd
	 * @param companyCd
	 * @param approverId
	 * @param memberId
	 * @param goalId
	 * @return
	 */
	GoalResponse findMembersPersonGoal(String seasonCd, String companyCd, Long userId, Long memberId, Long goalId);
	
	/**
	 * 조직목표 조회
	 * @param seasonCd
	 * @param companyCd
	 * @param approverId
	 * @param memberId
	 * @param goalId
	 * @return
	 */
	GoalResponse findMembersOrgGoal(String seasonCd, String companyCd, Long userId, Long memberId, Long goalId);

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
