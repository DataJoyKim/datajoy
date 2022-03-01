package com.d1.goalset.modules.goal.service;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;

public interface OrgGoalService {

	/**
	 * 목표 작성
	 * @param seasonCd 시즌코드
	 * @param companyCd 회사코드
	 * @param setterId 수립자ID
	 * @param params 요청파라미터
	 * @return
	 */
	Long write(String seasonCd, String companyCd, Long setterId, GoalWritingRequest params);

	/**
	 * 목표 수정
	 * @param seasonCd 시즌코드
	 * @param companyCd 회사코드
	 * @param goalId 목표ID
	 * @param setterId 수립자ID
	 * @param params 요청파라미터
	 */
	void update(String seasonCd, String companyCd, Long setterId, Long goalId, GoalWritingRequest params);
	
	/**
	 * 목표 삭제
	 * @param seasonCd
	 * @param companyCd
	 * @param setterId
	 * @param goalId
	 */
	void delete(String seasonCd, String companyCd, Long setterId, Long goalId);
	
	/**
	 * 목표 제출
	 * @param seasonCd
	 * @param companyCd
	 * @param setterId
	 */
	void submit(String seasonCd, String companyCd, Long setterId);
	
	/**
	 * 목표 회수
	 * @param seasonCd
	 * @param companyCd
	 * @param setterId
	 */
	void cancel(String seasonCd, String companyCd, Long setterId);
}
