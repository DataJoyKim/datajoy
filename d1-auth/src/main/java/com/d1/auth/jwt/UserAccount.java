package com.d1.auth.jwt;

import org.springframework.security.core.userdetails.User;

public class UserAccount extends User {
	private static final long serialVersionUID = -6515370828600235883L;
	
	private Account account;
	
	public UserAccount(Account account) {
		super(account.getEmail(), account.getPassword(), null);
		this.account = account;
	}

	public Account getAccount() {
		return this.account;
	}
}
