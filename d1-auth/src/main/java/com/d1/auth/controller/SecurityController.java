package com.d1.auth.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.auth.domain.Account;
import com.d1.auth.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SecurityController {
	
	private final AccountService accountService;

	@GetMapping(value = "/auth/v1/login")
	public String login(@RequestParam Map<String, String> params) {
		//인증
		
		//JWT Token 발급
		
		return "hello Spring security";
	}

	@GetMapping(value = "/auth/v1/sso")
	public String sso(@RequestParam Map<String, String> params) {
		// target token 가져오기
		
		// target 인증 api 호출 
		
		// 성공 시 인증
		
		// JWT Token 발급
		
		return "hello Spring security";
	}
	
	@GetMapping(value = "/auth/v1/user/{username}")
	public Account getUser(@PathVariable String username) {
		return accountService.findByEmail(username);
	}
	
	@GetMapping(value = "/auth/v1/{username}/{password}/{name}/{role}")
	public String getCreate(@PathVariable String username, @PathVariable String password, @PathVariable String name, @PathVariable String role) {
		Account account = new Account();
		account.setEmail(username);
		account.setPassword(password);
		account.setGroupCd("01");
		account.setRole(role);
		account.setUserNm(name);
		accountService.createNew(account);
		
		return "account create successful";
	}
}
