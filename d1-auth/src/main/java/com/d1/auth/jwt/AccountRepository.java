package com.d1.auth.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByEmail(String username);

	Account findByEmailAndPassword(String username, String password);
}
