package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalSettingResponse;

public interface PersonGoalQueryService {
	
	/**
	 * 목표 조회
	 * @param seasonCd 시즌코드
	 * @param companyCd 회사코드
	 * @param setterId 수립자ID
	 * @param goalId 목표ID
	 * @return
	 */
	GoalResponse findGoalBy(String seasonCd, String companyCd, Long setterId, Long goalId);

	/**
	 * 목표 목록 조회
	 * @param seasonCd 시즌코드
	 * @param companyCd 회사코드
	 * @param setterId 수립자ID
	 * @return
	 */
	List<GoalResponse> findGoalBy(String seasonCd, String companyCd, Long setterId);

	GoalSettingResponse findGoalSettingBy(String seasonCd, String companyCd, Long userId);
}
