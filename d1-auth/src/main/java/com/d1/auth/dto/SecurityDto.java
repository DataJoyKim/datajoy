package com.d1.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SecurityDto {
	
	@Setter @Getter
	public class LoginRequest {
		
		private String username;
		
		private String password;
		
	}
}
