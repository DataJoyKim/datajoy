package com.d1.auth.dto;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
public class SecurityDto {
	
	@Setter @Getter @NoArgsConstructor
	public static class LoginRequest {
		
		@NotNull
		private String username;
		
		@NotNull
		private String password;
		
	}
}
