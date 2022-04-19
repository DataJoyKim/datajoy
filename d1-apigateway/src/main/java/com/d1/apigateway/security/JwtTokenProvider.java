package com.d1.apigateway.security;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	
	private String secretKey = "D1-AUTH";
	
	/**
	 * 객체 초기화
	 * secretKey를 Base64로 인코딩
	 */
	@PostConstruct
	protected void init() {
	    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	/**
	 * 계정정보 가져오기
	 * @param token
	 * @return 계정정보
	 */
	public Account getAccount(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	    
	    return Account.createAccount(claims.getBody());
	}
	
	/**
	 * Request의 Header에서 Token 값을 가져옴
	 * @param request
	 * @return token
	 */
	public String resolveToken(ServerHttpRequest request) {
	    return request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
	}
	
	/**
	 * 토큰 유효성 검사
	 * 만료일 체크
	 * @param token - jwt token
	 * @return 유효하면 true
	 * @exception
	 * <pre>1) ExpiredJwtException : JWT를 생성할 때 지정한 유효기간 초과할 때.</pre>
	 * <pre>2) UnsupportedJwtException : 예상하는 형식과 일치하지 않는 특정 형식이나 구성의 JWT일 때</pre>
	 * <pre>3) MalformedJwtException : JWT가 올바르게 구성되지 않았을 때</pre>
	 * <pre>4) SignatureException :  JWT의 기존 서명을 확인하지 못했을 때</pre>
	 * <pre>5) IllegalArgumentException : </pre>
	 */
	public boolean validateToken(String token) {
	    try {
	        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	        return !claims.getBody().getExpiration().before(new Date());
	    } 
	    catch (SignatureException e) { 
	    	return false;
	    } 
	    catch (MalformedJwtException e) {  
	    	return false;
	    } 
	    catch (ExpiredJwtException e) {  
	    	return false;
	    } 
	    catch (UnsupportedJwtException e) {  
	    	return false;
	    } 
	    catch (IllegalArgumentException e) {  
	    	return false;
	    }
	}
}
