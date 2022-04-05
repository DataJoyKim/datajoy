package com.d1.apigateway.filter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			
			log.info("******************************* remote ip : " + request.getRemoteAddress());

			/*
			URI uri = exchange.getRequest().getURI();
			String url = UriComponentsBuilder.fromUri(uri).queryParam("empId", "1155991").build().toString();
			AddRequestParameterGatewayFilterFactory
			exchange.transformUrl(url);
			*/
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("empId", "1155991");
			ServerWebExchangeUtils.putUriTemplateVariables(exchange, uriVariables);
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				
				//TODO
			}));
			
		}, 1);
		
		return filter;
	}

	public static class Config {
		
	}
}
