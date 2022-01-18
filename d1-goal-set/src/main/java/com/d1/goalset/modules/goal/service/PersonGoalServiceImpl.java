package com.d1.goalset.modules.goal.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.modules.common.exception.BusinessException;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalPlan;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.domain.PersonGoal;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.PersonGoalSettingRepository;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.GoalSetter;

import lombok.RequiredArgsConstructor;

@Service("PersonGoalService")
@RequiredArgsConstructor
public class PersonGoalServiceImpl implements PersonGoalService {
	private final GoalRepository goalRepository;
	private final PersonGoalSettingRepository goalSettingRepository;
	private final GoalSettingValidator goalSettingValidator;
	
	@Transactional
	@Override
	public Goal write(GoalSetter goalSetter, GoalWritingRequest params) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		Set<GoalPlan> goalPlans = GoalPlan.createGoalPlans(params.getGoalPlans()); 
		
		Goal goal = Goal.createGoal(new PersonGoal(), goalSettingValidator, goalSetting, goalSetter, goalPlans, params);
		
		return goalRepository.save(goal);
	}

	@Transactional
	@Override
	public Goal updateBy(Long goalId, GoalSetter goalSetter, GoalWritingRequest params) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		Goal goal = goalRepository.findGoalBy(goalId)
								.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL));
		
		goal.update(goalSettingValidator, goalSetting, goalSetter, params);
		
		return goal;
	}

	@Transactional
	@Override
	public void deleteBy(Long goalId, GoalSetter goalSetter) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		Goal goal = goalRepository.findById(goalId).get();
		
		goal.delete(goalSettingValidator, goalSetting, goalSetter);
	}

	@Transactional
	@Override
	public void submit(GoalSetter goalSetter) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		goalSetting.submit(goalSettingValidator);
	}

	@Transactional
	@Override
	public void cancel(GoalSetter goalSetter) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		goalSetting.cancel(goalSettingValidator);
	}

}
