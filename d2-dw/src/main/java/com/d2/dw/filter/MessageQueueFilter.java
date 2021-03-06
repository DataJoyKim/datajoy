package com.d2.dw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.d2.dw.config.MessageQueuePolicy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageQueueFilter implements Filter {

	private final RabbitTemplate rabbitTemplate;
	
	private final MessageQueuePolicy messageQueuePolicy;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter Enter !!!");
		// HttpReuqest Wrapper
		System.out.println(request);
		
		// message queue send
		String message = "message queue test"; 
		try {
			rabbitTemplate.convertAndSend(messageQueuePolicy.getExchangeName(), messageQueuePolicy.getRoutingKey(), message);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		chain.doFilter(request, response);
	}
}
