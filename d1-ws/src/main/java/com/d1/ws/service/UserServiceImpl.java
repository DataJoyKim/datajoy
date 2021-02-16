package com.d1.ws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.d1.ws.domain.User;
import com.d1.ws.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User findUsersById(Long id) {
        return userRepository.findById(id).get();
    }

	@Override
	public Page<User> findAllUsers(PageRequest pageable) {
		return userRepository.findAll(pageable);
	}
}
