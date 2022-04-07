package com.d1.auth.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.auth.dto.SecurityDto.LoginRequest;
import com.d1.auth.error.AuthErrorCode;
import com.d1.auth.jwt.Account;
import com.d1.auth.jwt.JwtTokenGenerator;
import com.d1.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SecurityController {
	
	private final JwtTokenGenerator jwtTokenGenerator;

	@PostMapping(value = "/auth/v1/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest params, HttpServletResponse response) {
		
		Account account = jwtTokenGenerator.authentication(params.getUsername(), params.getPassword());
		if(account == null) {
			throw new BusinessException(AuthErrorCode.FAULT_ID_AND_PWD);
		}
		
		String token = jwtTokenGenerator.createToken(account);
		if(token == null) {
			throw new BusinessException(AuthErrorCode.FAILED_AUTH_TOKEN);
		}
		
		jwtTokenGenerator.setTokenIn(response, token);
        
		return ResponseEntity.ok().body(token);
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
