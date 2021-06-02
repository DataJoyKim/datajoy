package com.d1.auth.domain;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserAccount extends User {
	
	private Account account;
	
	public UserAccount(Account account) {
		super(account.getEmail(), account.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_"+account.getRole())));
		this.account = account;
	}

	public Account getAccount() {
		return this.account;
	}
}
