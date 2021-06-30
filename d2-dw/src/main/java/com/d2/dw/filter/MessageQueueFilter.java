package com.d2.dw.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.d2.dw.rabbitmq.MessageQueuePolicy;
import com.d2.dw.rabbitmq.SendMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageQueueFilter implements Filter {

	private final RabbitTemplate rabbitTemplate;
	
	private final MessageQueuePolicy messageQueuePolicy;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter Enter !!!");
		// message setting
		SendMessage message = new SendMessage();
		message.setDomain(request.getServerName());
		message.setPort(request.getServerPort());
		message.setRemoteIp(request.getRemoteAddr());
		message.setRemoteHost(request.getRemoteHost());
		message.setRequestBody(getRequestBody(request));
		message.setUserId("ks13ny");

		// message queue send
		rabbitTemplate.convertAndSend(messageQueuePolicy.getExchangeName(), messageQueuePolicy.getRoutingKey(), message);
		
		// do filter
		chain.doFilter(request, response);
	}
	
	private String getRequestBody(ServletRequest request) throws IOException {
		 
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        return body;
    }
}
