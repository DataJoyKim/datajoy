package com.d1.goalset.modules.user.service.query;

import com.d1.goalset.modules.user.dto.UserDto.UserResponse;

public interface UserQueryService {
	UserResponse findUserResponse(Long userId);
}
