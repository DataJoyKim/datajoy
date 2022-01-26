package com.d1.goalset.modules.user.service;

import com.d1.goalset.modules.user.domain.User;

public interface UserQueryService {
	User findUserBy(Long userId);
}
