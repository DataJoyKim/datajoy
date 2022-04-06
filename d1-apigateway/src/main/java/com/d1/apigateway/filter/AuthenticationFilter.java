package com.d1.apigateway.filter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.d1.apigateway.error.SecurityErrorCode;
import com.d1.apigateway.security.JwtTokenProvider;
import com.d1.common.exception.BusinessException;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	private final JwtTokenProvider jwtTokenProvider;
	
	public AuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		super(Config.class);
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();

	        String token = jwtTokenProvider.resolveToken(request);
	        
	        if (token == null) {
	        	throw new BusinessException(SecurityErrorCode.NOT_FOUND_AUTHENTICATION);
	        }
	        
	        if (jwtTokenProvider.validateToken(token) == false) {
	        	throw new BusinessException(SecurityErrorCode.FAULT_AUTHENTICATION);
	        }
	        
	        Authentication authentication = jwtTokenProvider.getAuthentication(token);
			
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("empId", authentication.getName());
			
			ServerWebExchangeUtils.putUriTemplateVariables(exchange, uriVariables);
			
			return chain.filter(exchange);
		}, 1);
		
		return filter;
	}

	public static class Config {
		
	}
}
