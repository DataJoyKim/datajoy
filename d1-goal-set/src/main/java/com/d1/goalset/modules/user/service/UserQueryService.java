package com.d1.goalset.modules.user.service;

import java.util.List;

import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;

public interface UserQueryService {
	User findUser(Long userId);
	UserResponse findUserResponse(Long userId);
	List<UserResponse> findMembers(Long userId);
}
