package com.d1.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.auth.domain.Account;
import com.d1.auth.dto.SecurityDto.LoginRequest;
import com.d1.auth.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SecurityController {
	
	private final JwtTokenProvider jwtTokenProvider;

	@PostMapping(value = "/auth/v1/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest params, HttpServletResponse response) {
		// 인증
		Account account = jwtTokenProvider.authentication(params.getUsername(), params.getPassword());
		if(account == null) {
			//AUTHENTICATION_FAILED
			return ResponseEntity.badRequest().build();
		}
		
		// Token 생성
		String token = jwtTokenProvider.createToken(account);
		if(token == null) {
			// CREATE_TOKEN_FAILED
			return ResponseEntity.noContent().build();
		}
		
		// Token header 세팅
		jwtTokenProvider.setTokenIn(response, token);
        
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/auth/v1/sso")
	public String sso(@RequestParam Map<String, String> params) {
		// target token 가져오기
		
		// target 인증 api 호출 
		
		// 성공 시 인증
		
		// JWT Token 발급
		
		return "hello Spring security";
	}
}
