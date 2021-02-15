package com.d1.ws.service.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.d1.ws.domain.User;
import com.d1.ws.repository.UserRepository;
import com.d1.ws.service.dto.UserDTO;

@Service("UserQueryService")
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    UserRepository userRepository;

    public UserDTO findUsersById(Long id) {
    	return UserDTO.convert(userRepository.findById(id).get()); 
    }

	@Override
	public Page<UserDTO> findAllUsers(PageRequest pageable) {
		Page<User> users = userRepository.findAll(pageable);
		return users.map(o -> UserDTO.convert(o));
	}
}
