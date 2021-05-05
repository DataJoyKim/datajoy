package com.d1.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.d1.auth.domain.Account;
import com.d1.auth.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
	
	private final AccountRepository accountRepositroy;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepositroy.findByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return User.builder()
				.username(account.getEmail())
				.password(account.getPassword())
				.roles(account.getRole())
				.build();
	}
	
	
}
