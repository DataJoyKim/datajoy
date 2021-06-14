package com.d2.dw.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageQueueController {

	private final RabbitTemplate rabbitTemplate;
	
	@Value("${myconfig.exchangename}")
    private String exchangeName;

	@Value("${myconfig.routingkey}")
	private String routingKey;
	
	@GetMapping(value="/api/queue/test")
	public void getMessageQueueTest() {
		String message = "message queue test";
		rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
	}
	
}
