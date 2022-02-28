package com.d1.goalset.modules.goal.service;

public interface MemberService {

	/**
	 * 조직원의 목표를 승인
	 * @param companyCd 회사코드
	 * @param seasonCd 시즌코드
	 * @param approverId 승인자ID
	 * @param memberId 조직원 ID
	 */
	void approve(String seasonCd, String companyCd, Long approverId, Long memberId);

}
