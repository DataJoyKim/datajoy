package com.d1.ws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.d1.ws.domain.User;

public interface UserService {
	User findUsersById(Long id);

	Page<User> findAllUsers(PageRequest pageable);
}
