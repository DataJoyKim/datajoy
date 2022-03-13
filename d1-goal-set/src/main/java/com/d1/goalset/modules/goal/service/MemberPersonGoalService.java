package com.d1.goalset.modules.goal.service;

public interface MemberPersonGoalService {

	/**
	 * 조직원의 개인목표 승인
	 * @param companyCd 회사코드
	 * @param seasonCd 시즌코드
	 * @param approverId 승인자ID
	 * @param memberId 조직원 ID
	 */
	void approve(String seasonCd, String companyCd, Long userId, Long memberId);
}
