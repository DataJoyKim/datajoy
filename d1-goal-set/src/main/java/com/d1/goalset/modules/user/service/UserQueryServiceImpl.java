package com.d1.goalset.modules.user.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.error.UserErrorCode;
import com.d1.goalset.modules.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("UserQueryService")
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

	private final UserRepository goalSetterRepository;
	
	@Override 
	public User findUserBy(Long userId) {
		return goalSetterRepository.findById(userId).orElseThrow(() -> new BusinessException(UserErrorCode.NULL_GOAL_SETTER)); 
	}
 
}
