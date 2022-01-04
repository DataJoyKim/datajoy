package com.d1.goalset.modules.goal.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalPlan;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalPlanWritingDto;
import com.d1.goalset.modules.goal.dto.PersonGoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.repository.GoalPlanRepository;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;

import lombok.RequiredArgsConstructor;

@Service("PersonGoalService")
@RequiredArgsConstructor
public class PersonGoalServiceImpl implements PersonGoalService {
	private final GoalRepository goalRepository;
	private final GoalPlanRepository goalPlanRepository;
	private final GoalSettingRepository goalSettingRepository;

	@Transactional
	@Override
	public Goal write(GoalWritingRequest params) {
		
		GoalSetting goalSetting = new PersonGoalSetting();
		Goal goal = goalSetting.write(null, null, params);
		
		Goal savedGoal = goalRepository.save(goal);
		
		Set<GoalPlan> savedGoalPlans = saveGoalPlan(goal.getGoalPlans());
		
		
		return null;
	}

	private Set<GoalPlan> saveGoalPlan(Set<GoalPlan> goalPlans) {
		for(GoalPlan plan : goalPlans) {
			GoalPlan savedGoalPlan = goalPlanRepository.save(plan);
		}
		return null;
	}

	@Transactional
	@Override
	public Goal update(GoalWritingRequest params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
