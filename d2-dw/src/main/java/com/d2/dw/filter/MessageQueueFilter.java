package com.d2.dw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.d2.dw.rabbitmq.MessageQueuePolicy;
import com.d2.dw.rabbitmq.SendMessage;
import com.d2.dw.util.ServletRequestUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageQueueFilter implements Filter {

	private final RabbitTemplate rabbitTemplate;
	
	private final MessageQueuePolicy messageQueuePolicy;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filter Enter !!!");
		// get authentication
		// TODO
		
		// message setting
		SendMessage message = new SendMessage();
		message.setDomain(request.getServerName());
		message.setPort(request.getServerPort());
		message.setRemoteIp(request.getRemoteAddr());
		message.setRemoteHost(request.getRemoteHost());
		message.setRequestBody(ServletRequestUtil.getRequestBody(request));
		message.setUserId("ks13ny");

		// message queue send
		rabbitTemplate.convertAndSend(messageQueuePolicy.getExchangeName(), messageQueuePolicy.getRoutingKey(), message);
		
		// do filter
		chain.doFilter(request, response);
	}
}
