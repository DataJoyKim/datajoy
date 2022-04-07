package com.d1.auth.jwt;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenGenerator {
	
	private String secretKey = "D1-AUTH";
	
	private long tokenValidTime = 30 * 60 * 1000L;
	
	private final AccountService userDetailsService;
	
	/**
	 * 객체 초기화
	 * secretKey를 Base64로 인코딩
	 */
	@PostConstruct
	protected void init() {
	    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	/**
	 * 토큰 생성
	 * @param account - 계정정보
	 * @return token
	 */
	public String createToken(Account account) {
	    Date now = new Date();
	    return Jwts.builder()
		            .setClaims(AccountClaim.createClaims(account)) // 정보 저장
		            .setIssuedAt(now) // 토큰 발행 시간 정보
		            .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
		            .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 secret key 셋팅
		            .compact();
	}
	
	/**
	 * response header에 Token 셋팅
	 * @param response
	 * @param token
	 */
	public void setTokenIn(HttpServletResponse response, String token) {
        response.setHeader("Authorization", token); 
	}

	/**
	 * 인증
	 * @param username
	 * @param password
	 * @return 인증정보
	 */
	public Account authentication(String username, String password) {
		return userDetailsService.authentication(username, password);
	}
}
