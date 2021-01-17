package com.d1.ws.service;

import java.util.List;

import com.d1.ws.domain.User;

public interface UserService {
	List<User> findUsersById(long id);
}
