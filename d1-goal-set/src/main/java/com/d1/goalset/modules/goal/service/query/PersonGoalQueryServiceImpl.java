package com.d1.goalset.modules.goal.service.query;

import org.springframework.stereotype.Service;

import com.d1.goalset.modules.common.exception.BusinessException;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalSettingResponse;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.user.domain.GoalSetter;

import lombok.RequiredArgsConstructor;

@Service("PersonGoalQueryService")
@RequiredArgsConstructor
public class PersonGoalQueryServiceImpl implements PersonGoalQueryService {
	
	private final GoalRepository goalRepository;
	
	@Override
	public GoalSettingResponse getPersonGoalSetting(GoalSetter goalSetter) {
		 PersonGoalSetting goalSetting = goalRepository.findPersonGoalSettingBy(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		 return GoalSettingResponse.of(goalSetting);
	}

	@Override
	public GoalResponse getGoal(Long goalId) { 
		Goal goal = goalRepository.findGoalBy(goalId)
								.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL));
		
		return GoalResponse.of(goal);
	}
 
}
