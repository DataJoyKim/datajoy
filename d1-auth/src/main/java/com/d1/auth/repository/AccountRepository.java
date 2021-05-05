package com.d1.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d1.auth.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByEmail(String username);

}
