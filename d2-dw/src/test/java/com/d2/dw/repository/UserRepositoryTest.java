package com.d2.dw.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("사용자 DB 조회 테스트")
	void getUsersTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size() >= 0 ? true : false);
	}
}
