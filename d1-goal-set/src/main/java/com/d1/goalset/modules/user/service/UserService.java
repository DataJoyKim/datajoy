package com.d1.goalset.modules.user.service;

import com.d1.goalset.modules.user.domain.User;

public interface UserService {
	User findUser(Long userId);
	
	User findApprover(Long userId);
}
