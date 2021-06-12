package com.d1.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueConsumer {
	
    @RabbitListener(queues = "${myconfig.queuename}")
    public void processMessage(Message message) {
        System.out.println(message);
    }
}
