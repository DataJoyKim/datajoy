package com.d1.goalset.modules.goal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalPlan;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.domain.PersonGoal;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.error.PersonGoalErrorCode;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.goal.validator.GoalSettingValidator;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service("PersonGoalService")
@RequiredArgsConstructor
public class PersonGoalServiceImpl implements PersonGoalService {
	private final GoalRepository goalRepository;
	private final GoalSettingRepository goalSettingRepository;
	private final GoalSettingValidator goalSettingValidator;
	private final UserService userService; 
	
	@Transactional
	@Override
	public Long write(String seasonCd, String companyCd, Long setterId, GoalWritingRequest params) {
		User setter = userService.findUser(setterId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndSetterAndGoalType(seasonCd, companyCd, setter, GoalTypeCode.PERSON_GOAL)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		List<GoalPlan> goalPlans = GoalPlan.createGoalPlans(params.getGoalPlans()); 
		
		Goal goal = Goal.createGoal(new PersonGoal(), setter, goalPlans, params);
		
		goalSetting.writeGoal(goalSettingValidator, setter, goal);
		
		goalSettingRepository.save(goalSetting);
		
		return goal.getId();
	}

	@Transactional
	@Override
	public void update(String seasonCd, String companyCd, Long setterId, Long goalId, GoalWritingRequest params) {
		User setter = userService.findUser(setterId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndSetterAndGoalType(seasonCd, companyCd, setter, GoalTypeCode.PERSON_GOAL)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		Goal goal = goalRepository.findGoalBy(setter.getId(), goalId)
									.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL));
		
		goal.update(goalSettingValidator, goalSetting, setter, params);
	}

	@Transactional
	@Override
	public void delete(String seasonCd, String companyCd, Long setterId, Long goalId) {
		User setter = userService.findUser(setterId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndSetterAndGoalType(seasonCd, companyCd, setter, GoalTypeCode.PERSON_GOAL)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		Goal goal = goalRepository.findById(goalId).get();
		
		goal.delete(goalSettingValidator, goalSetting, setter);
	}

	@Transactional
	@Override
	public void submit(String seasonCd, String companyCd, Long setterId) {
		User setter = userService.findUser(setterId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndSetterAndGoalType(seasonCd, companyCd, setter, GoalTypeCode.PERSON_GOAL)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		List<Goal> goals = goalRepository.findGoalBy(goalSetting.getTargetId());
		
		goalSetting.submit(goalSettingValidator, goals);
	}

	@Transactional
	@Override
	public void cancel(String seasonCd, String companyCd, Long setterId) {
		User setter = userService.findUser(setterId);
		
		GoalSetting goalSetting = goalSettingRepository.findBySeasonCdAndCompanyCdAndSetterAndGoalType(seasonCd, companyCd, setter, GoalTypeCode.PERSON_GOAL)
														.orElseThrow(() -> new BusinessException(PersonGoalErrorCode.NOT_FOUND_GOAL_SETTING));
		
		List<Goal> goals = goalRepository.findGoalBy(goalSetting.getTargetId());
		
		goalSetting.cancel(goalSettingValidator, goals);
	}
}
