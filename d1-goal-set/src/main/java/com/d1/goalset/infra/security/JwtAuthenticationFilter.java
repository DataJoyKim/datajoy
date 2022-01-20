package com.d1.goalset.infra.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.d1.goalset.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
	
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 헤더에서 JWT 받아옴
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        
        // 토큰 미존재 시
        if (token == null || jwtTokenProvider.validateToken(token) == false) {
        	throw new BusinessException(SecurityErrorCode.FAILED_AUTHENTICATION);
        }
        
        // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옴
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        // SecurityContext 에 Authentication 객체를 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // do filter
        chain.doFilter(request, response);
	}
}
