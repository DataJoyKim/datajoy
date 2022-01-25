package com.d1.goalset.modules.goal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalPlan;
import com.d1.goalset.modules.goal.domain.PersonGoal;
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
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
	public Long write(GoalSetter goalSetter, GoalWritingRequest params) {
		PersonGoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		List<GoalPlan> goalPlans = GoalPlan.createGoalPlans(params.getGoalPlans()); 
		
		Goal goal = Goal.createGoal(new PersonGoal(), goalSettingValidator, goalSetting, goalSetter, goalPlans, params);
		
		goalRepository.save(goal);
		
		return goal.getId();
	}

	@Transactional
	@Override
	public void update(Long goalId, GoalSetter goalSetter, GoalWritingRequest params) {
		PersonGoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		Goal goal = goalRepository.findGoalBy(goalSetter.getId(), goalId)
									.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL));
		
		goal.update(goalSettingValidator, goalSetting, goalSetter, params);
	}

	@Transactional
	@Override
	public void delete(Long goalId, GoalSetter goalSetter) {
		PersonGoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		Goal goal = goalRepository.findById(goalId).get();
		
		goal.delete(goalSettingValidator, goalSetting, goalSetter);
	}

	@Transactional
	@Override
	public void submit(GoalSetter goalSetter) {
		PersonGoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		List<Goal> goals = goalRepository.findGoalBy(goalSetting.getTargetId());
		
		goalSetting.submit(goalSettingValidator, goals);
	}

	@Transactional
	@Override
	public void cancel(GoalSetter goalSetter) {
		PersonGoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		List<Goal> goals = goalRepository.findGoalBy(goalSetting.getTargetId());
		
		goalSetting.cancel(goalSettingValidator, goals);
	}

}
