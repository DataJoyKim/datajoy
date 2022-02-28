package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalSettingResponse;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("PersonGoalQueryService")
@RequiredArgsConstructor
public class PersonGoalQueryServiceImpl implements PersonGoalQueryService {
	 
	private final GoalRepository goalRepository;
	private final GoalSettingRepository goalSettingRepository;
	private final UserService userService;

	@Override
	public GoalResponse findGoalBy(String seasonCd, String companyCd, Long setterId, Long goalId) { 
		Goal goal = goalRepository.findGoalBy(setterId, goalId)
								.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL));
		
		return GoalResponse.of(goal);
	}

	@Override
	public List<GoalResponse> findGoalBy(String seasonCd, String companyCd, Long setterId) {
		List<Goal> goals = goalRepository.findGoalBy(setterId);
		
		return GoalResponse.of(goals);
	}

	@Override
	public GoalSettingResponse findGoalSettingBy(String seasonCd, String companyCd, Long setterId) {
		User setter = userService.findUser(setterId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndSetter(seasonCd, companyCd, setter)
																.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		return GoalSettingResponse.of(goalSetting);
	}
 
}
