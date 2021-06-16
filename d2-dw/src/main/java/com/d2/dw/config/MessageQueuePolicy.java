package com.d2.dw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class MessageQueuePolicy {
	
	@Value("${myconfig.queuename}")
	private String queueName;

	@Value("${myconfig.exchangename}")
	private String exchangeName;
	
	@Value("${myconfig.routingkey}")
	private String routingKey;
}
