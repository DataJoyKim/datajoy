package com.d1.goalset.modules.goal.service;

import com.d1.goalset.modules.user.domain.User;

public interface MemberService {

	/**
	 * 조직원의 목표를 승인
	 * @param companyCd 회사코드
	 * @param seasonCd 시즌코드
	 * @param approver 승인자
	 * @param memberId 조직원 ID
	 */
	void approve(String seasonCd, String companyCd, User approver, Long memberId);

}
