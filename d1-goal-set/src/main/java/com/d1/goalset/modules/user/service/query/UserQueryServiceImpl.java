package com.d1.goalset.modules.user.service.query;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;
import com.d1.goalset.modules.user.error.UserErrorCode;
import com.d1.goalset.modules.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("UserQueryService")
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

	private final UserRepository userRepository;

	@Override
	public UserResponse findUserResponse(Long userId) {
		return UserResponse.of(userRepository.findById(userId).orElseThrow(() -> new BusinessException(UserErrorCode.NULL_GOAL_SETTER)));
	}
}
