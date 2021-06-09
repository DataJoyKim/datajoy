package com.d1.auth.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.d1.auth.jwt.domain.UserAccount;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
	
	private final AccountRepository accountRepositroy;

	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepositroy.findByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserAccount(account);
	}
	
	public Account createNew(Account account) {
		account.encodePassword(passwordEncoder);
		return accountRepositroy.save(account);
	}
	
	public Account findByEmail(String username) {
		Account account = accountRepositroy.findByEmail(username);
		return account;
	}

	
	public Account authentication(String username, String password) {
		return accountRepositroy.findByEmailAndPassword(username, password);
	}
}
