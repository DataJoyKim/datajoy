package com.d2.dw.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.d2.dw.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void test() {
		List<User> users = userRepository.findAll();
		
		System.out.println(users);
	}
}
