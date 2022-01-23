package com.d1.goalset.modules.user.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.user.domain.GoalSetter;
import com.d1.goalset.modules.user.error.GoalSetterErrorCode;
import com.d1.goalset.modules.user.repository.GoalSetterRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("UserQueryService")
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

	private final GoalSetterRepository goalSetterRepository;
	
	@Override 
	public GoalSetter findGoalSetterBy(Long userId) {
		return goalSetterRepository.findById(userId).orElseThrow(() -> new BusinessException(GoalSetterErrorCode.NULL_GOAL_SETTER)); 
	}
 
}
