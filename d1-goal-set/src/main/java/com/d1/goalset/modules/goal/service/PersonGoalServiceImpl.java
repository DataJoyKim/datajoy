package com.d1.goalset.modules.goal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.modules.goal.domain.Goal;
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
	public GoalSetting write(GoalSetter goalSetter, GoalWritingRequest params) {
		
		// 목표계획 생성
		
		// 목표 생성
		
		// 목표수립 생성
		
		GoalSetting goalSetting = new PersonGoalSetting();
		Goal goal = goalSetting.write(goalSettingValidator, goalSetter, params);
		
		GoalSetting savedGoalSetting = goalSettingRepository.save(goalSetting);
		
		return savedGoalSetting;
	}

	@Transactional
	@Override
	public GoalSetting updateBy(Long goalCd, GoalSetter goalSetter, GoalWritingRequest params) {
		
		return null;
	}

	@Override
	public void deleteBy(Long goalCd, GoalSetter goalSetter) {
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
