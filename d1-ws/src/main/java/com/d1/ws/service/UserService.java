package com.d1.ws.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.d1.ws.domain.User;

public interface UserService {
	List<User> findUsersById(long id);

	Page<User> findAllUsers(PageRequest pageable);
}
