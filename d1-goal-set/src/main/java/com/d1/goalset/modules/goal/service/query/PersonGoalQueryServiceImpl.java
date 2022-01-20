package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;

import lombok.RequiredArgsConstructor;

@Service("PersonGoalQueryService")
@RequiredArgsConstructor
public class PersonGoalQueryServiceImpl implements PersonGoalQueryService {
	 
	private final GoalRepository goalRepository;

	@Override
	public GoalResponse findGoalBy(Long userId, Long goalId) { 
		Goal goal = goalRepository.findGoalBy(userId, goalId)
								.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL));
		
		return GoalResponse.of(goal);
	}

	@Override
	public List<GoalResponse> findGoalBy(Long userId) {
		List<Goal> goals = goalRepository.findGoalBy(userId);

		return GoalResponse.of(goals);
	}
 
}
