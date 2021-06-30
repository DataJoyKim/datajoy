package com.d2.dw.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d2.dw.rabbitmq.MessageQueuePolicy;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageQueueController {

	private final RabbitTemplate rabbitTemplate;
	
	private final MessageQueuePolicy messageQueuePolicy;
	
	@GetMapping(value="/api/queue/test")
	public void getMessageQueueTest() {
		for(int i=0; i<10000; i++) {
			String message = "message queue test_"+i;
			System.out.println(message);
			rabbitTemplate.convertAndSend(messageQueuePolicy.getExchangeName(), messageQueuePolicy.getRoutingKey(), message);
		}
		System.out.println("mq call after...");
	}
	
}
