package com.d1.goalset.modules.goal.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalPlan;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.repository.GoalPlanRepository;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.GoalSetter;

import lombok.RequiredArgsConstructor;

@Service("PersonGoalService")
@RequiredArgsConstructor
public class PersonGoalServiceImpl implements PersonGoalService {
	private final GoalRepository goalRepository;
	private final GoalPlanRepository goalPlanRepository;
	private final GoalSettingRepository goalSettingRepository;
	private final GoalSettingValidator goalSettingValidator;
	
	@Transactional
	@Override
	public Goal write(GoalSetter goalSetter, GoalWritingRequest params) {
		
		GoalSetting goalSetting = new PersonGoalSetting();
		Goal goal = goalSetting.write(goalSettingValidator, goalSetter, params);
		
		Goal savedGoal = goalRepository.save(goal);
		
		Set<GoalPlan> savedGoalPlans = saveGoalPlan(goal.getGoalPlans());
		savedGoal.setGoalPlans(savedGoalPlans);
		
		return savedGoal;
	}

	private Set<GoalPlan> saveGoalPlan(Set<GoalPlan> goalPlans) {
		Set<GoalPlan> savedGoalPlans = new HashSet<>();
		
		for(GoalPlan plan : goalPlans) {
			savedGoalPlans.add(goalPlanRepository.save(plan));
		}
		
		return savedGoalPlans;
	}

	@Transactional
	@Override
	public Goal update(GoalSetter goalSetter, GoalWritingRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
