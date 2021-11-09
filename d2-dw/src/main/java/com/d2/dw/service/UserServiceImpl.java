package com.d2.dw.service;

import org.springframework.stereotype.Service;

import com.d2.dw.domain.User;
import com.d2.dw.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
 
}
