package com.d1.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageQueueProducer {
	
    @RabbitListener(queues = "TestQ")
    public void processMessage(String content) {
        System.out.println(content);
    }
}
