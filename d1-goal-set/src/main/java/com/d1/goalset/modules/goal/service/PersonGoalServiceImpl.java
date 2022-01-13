package com.d1.goalset.modules.goal.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalPlan;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.GoalSetter;

import lombok.RequiredArgsConstructor;

@Service("PersonGoalService")
@RequiredArgsConstructor
public class PersonGoalServiceImpl implements PersonGoalService {
	private final GoalRepository goalRepository;
	private final GoalSettingRepository goalSettingRepository;
	private final GoalSettingValidator goalSettingValidator;
	
	@Transactional
	@Override
	public Goal write(GoalSetter goalSetter, GoalWritingRequest params) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter).get();
		
		Set<GoalPlan> goalPlans = GoalPlan.createGoalPlans(params.getGoalPlans()); 
		
		Goal goal = Goal.createGoal(goalSettingValidator, goalSetting, goalSetter, goalPlans, params);
		
		return goalRepository.save(goal);
	}

	@Transactional
	@Override
	public Goal updateBy(Long goalId, GoalSetter goalSetter, GoalWritingRequest params) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter).get();
		Goal goal = goalRepository.findById(goalId).get();
		
		goal.update(goalSettingValidator, goalSetting, goalSetter, params);
		
		return goal;
	}

	@Transactional
	@Override
	public void deleteBy(Long goalId, GoalSetter goalSetter) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter).get();
		Goal goal = goalRepository.findById(goalId).get();
		
		goal.delete(goalSettingValidator, goalSetting, goalSetter);
	}

	@Transactional
	@Override
	public void submit(GoalSetter goalSetter) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter).get();
		
		goalSetting.submit(goalSettingValidator);
	}

	@Transactional
	@Override
	public void cancel(GoalSetter goalSetter) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter).get();
		
		goalSetting.cancel(goalSettingValidator);
	}

}
