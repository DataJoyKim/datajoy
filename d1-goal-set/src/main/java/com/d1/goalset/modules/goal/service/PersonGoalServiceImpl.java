package com.d1.goalset.modules.goal.service;

import org.springframework.stereotype.Service;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;

import lombok.RequiredArgsConstructor;

@Service("PersonGoalService")
@RequiredArgsConstructor
public class PersonGoalServiceImpl implements PersonGoalService {
	private final GoalRepository goalRepository;
	private final GoalSettingRepository goalSettingRepository;
	
	@Override
	public Goal write() {
		return null;
	}

	@Override
	public Goal update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
