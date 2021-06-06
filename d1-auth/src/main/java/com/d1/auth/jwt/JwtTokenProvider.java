package com.d1.auth.jwt;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.d1.auth.jwt.domain.Account;
import com.d1.auth.jwt.service.AccountService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	
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
	    Claims claims = Jwts.claims().setSubject(account.getEmail());
	    claims.put("role", account.getRole());
	    Date now = new Date();
	    return Jwts.builder()
		            .setClaims(claims) // 정보 저장
		            .setIssuedAt(now) // 토큰 발행 시간 정보
		            .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
		            .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 secret key 셋팅
		            .compact();
	}
	
	/**
	 * 인증정보 가져오기
	 * @param token
	 * @return 인증정보
	 */
	public Authentication getAuthentication(String token) {
		String username = getSubject(token);
	    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
	/**
	 * 토큰에서 subject 값 조회
	 * @param token - 토큰
	 * @return subject
	 */
	public String getSubject(String token) {
	    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	/**
	 * Request의 Header에서 Token 값을 가져옴
	 * @param request
	 * @return token
	 */
	public String resolveToken(HttpServletRequest request) {
	    return request.getHeader("Authorization");
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
	 * 토큰 유효성 검사
	 * 만료일 체크
	 * @param token - jwt token
	 * @return 유효하면 true
	 */
	public boolean validateToken(String token) {
	    try {
	        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	        return !claims.getBody().getExpiration().before(new Date());
	    } 
	    catch (Exception e) {
	        return false;
	    }
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
