package com.d2.dw.filter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.d2.dw.config.MessageQueuePolicy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageQueueFilter {

	private final RabbitTemplate rabbitTemplate;
	
	private final MessageQueuePolicy messageQueuePolicy;
	
	public void getMessageQueueTest() {
		for(int i=0; i<10000; i++) {
			String message = "message queue test_"+i;
			System.out.println(message);
			rabbitTemplate.convertAndSend(messageQueuePolicy.getExchangeName(), messageQueuePolicy.getRoutingKey(), message);
		}
		System.out.println("mq call after...");
	}
}
