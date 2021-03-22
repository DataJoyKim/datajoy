package com.d2.dw.service.query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.dto.UserDTO;
import com.d2.dw.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("UserQueryService")
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

	private final UserRepository userRepository;
	
	@Override
	public UserDTO getUser(Long userId) {
		return UserDTO.convert(userRepository.findById(userId).get());
	}
	
}
