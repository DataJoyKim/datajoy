package com.d1.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.d1.ws.domain.User;
import com.d1.ws.repository.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findUsersById(long id) {
        List<User> list = userRepository.findAllById(id);
        
        return list;
    }

	@Override
	public Page<User> findAllUsers(PageRequest pageable) {
		return userRepository.findAll(pageable);
	}
}
