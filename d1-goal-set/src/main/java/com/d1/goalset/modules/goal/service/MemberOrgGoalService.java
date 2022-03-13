package com.d1.goalset.modules.goal.service;

public interface MemberOrgGoalService {
	/**
	 * 조직원의 조직목표 승인
	 * @param seasonCd
	 * @param companyCd
	 * @param approverId
	 * @param memberId
	 */
	void approve(String seasonCd, String companyCd, Long userId, Long memberId);

}
