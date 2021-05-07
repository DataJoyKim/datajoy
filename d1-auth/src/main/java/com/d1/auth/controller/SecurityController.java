package com.d1.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.d1.auth.domain.Account;
import com.d1.auth.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SecurityController {
	
	private final AccountService accountService;
	
	@GetMapping(value = "/auth/v1/")
	public String getToken(Principal principal) {
		System.out.println("인증된사용자 : ");
		System.out.println(principal);
		return "hello Spring security";
	}

	@GetMapping(value = "/auth/v1/test")
	public String getTest() {
		return "hello Spring security";
	}
	
	@GetMapping(value = "/auth/v1/{username}/{password}")
	public String getCreate(@PathVariable String username,@PathVariable String password) {
		Account account = new Account();
		account.setEmail(username);
		account.setPassword(password);
		account.setGroupCd("01");
		account.setRole("USER");
		account.setUserNm("김낙영2");
		accountService.createNew(account);
		
		return "account create successful";
	}
}
