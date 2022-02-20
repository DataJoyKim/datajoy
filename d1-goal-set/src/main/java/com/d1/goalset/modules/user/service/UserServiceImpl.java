package com.d1.goalset.modules.user.service;

import org.springframework.stereotype.Service;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.error.UserErrorCode;
import com.d1.goalset.modules.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override 
	public User findUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new BusinessException(UserErrorCode.NULL_GOAL_SETTER)); 
	}
}
