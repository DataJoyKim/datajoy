package com.d1.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
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
}
