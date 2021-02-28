package com.d2.dw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2.dw.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
