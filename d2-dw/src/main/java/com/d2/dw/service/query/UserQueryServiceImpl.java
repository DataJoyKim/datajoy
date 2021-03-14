package com.d2.dw.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.d2.dw.dto.UserDTO;
import com.d2.dw.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service("UserQueryService")
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

	private final UserRepository userRepository;
	
}
