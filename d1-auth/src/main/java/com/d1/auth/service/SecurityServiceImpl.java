package com.d1.auth.service;

import org.springframework.stereotype.Service;

import com.d1.auth.dto.SecurityDto.LoginRequest;
import com.d1.auth.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service("SecurityService")
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService{
	
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public void login(LoginRequest params) throws Exception {
		// 토큰 생성
		String token = jwtTokenProvider.createToken(params.getUsername(), null);
		
		// Authorization header 셋팅
	}

}
