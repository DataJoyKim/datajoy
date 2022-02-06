package com.d1.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggerFilter extends AbstractGatewayFilterFactory<LoggerFilter.Config> {

	@Override
	public GatewayFilter apply(Config config) {
		GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			
			log.info("******************************* remote ip : " + request.getRemoteAddress());

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				//TODO
			}));
			
		}, 1);
		
		return filter;
	}

	public static class Config {
		
	}
}
