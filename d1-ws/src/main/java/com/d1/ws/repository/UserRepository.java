package com.d1.ws.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d1.ws.domain.User;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);

}
