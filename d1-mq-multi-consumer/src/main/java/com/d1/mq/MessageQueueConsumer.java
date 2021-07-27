package com.d1.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class MessageQueueConsumer {
	
    @RabbitListener(queues = "${myconfig.queuename}")
    public void processMessage(SendMessage message) throws Exception {
        System.out.println(message);
        System.out.println("test : "+message);
    }
}
