package com.d1.goalset.modules.user.service;

import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;

public interface UserQueryService {
	User findUserBy(Long userId);
	UserResponse findUserResponseBy(Long userId);
}
