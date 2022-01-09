package com.d1.goalset.modules.goal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
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
		
		Goal goal = Goal.createGoal(goalSettingValidator, goalSetting, goalSetter, params);
		
		return goalRepository.save(goal);
	}

	@Transactional
	@Override
	public Goal updateBy(Long goalId, GoalSetter goalSetter, GoalWritingRequest params) {
		GoalSetting goalSetting = goalSettingRepository.findByGoalSetter(goalSetter).get();
		
		Goal goal = goalRepository.findById(goalId).get();
		
		goal.update(goalSettingValidator, goalSetting, goalSetter, params);

		return goalRepository.save(goal);
	}

	@Override
	public void deleteBy(Long goalId, GoalSetter goalSetter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submit(GoalSetter goalSetter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel(GoalSetter goalSetter) {
		// TODO Auto-generated method stub
		
	}

}
