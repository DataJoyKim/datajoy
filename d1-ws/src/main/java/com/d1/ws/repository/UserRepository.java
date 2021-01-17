package com.d1.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d1.ws.domain.User;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAllBySeq(long id);

}
