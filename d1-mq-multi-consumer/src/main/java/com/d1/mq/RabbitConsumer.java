package com.d1.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

	@RabbitListener(bindings = @QueueBinding(value = @Queue("ny-queue") , exchange = @Exchange(value="ny-exchange", type="topic") , key = "ny.#") , containerFactory = "factoryServer01")
	public void receiveServer01(SendMessage obj) {
			
	}
	
	@RabbitListener(bindings = @QueueBinding(value = @Queue("ny-queue") , exchange = @Exchange(value="ny-exchange", type="topic") , key = "ny.#") , containerFactory = "factoryServer02")
	public void receiveServer02(SendMessage obj) {
			
	}
}
