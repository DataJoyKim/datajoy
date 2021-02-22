package com.d1.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@GetMapping(value = "/security/v1/token")
	public String getToken() {
		return "hello Spring security";
	}

}
