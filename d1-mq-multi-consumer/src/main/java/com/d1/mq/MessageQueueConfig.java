package com.d1.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class MessageQueueConfig {

	@Value("${myconfig.queuename}")
    private String queueName;

	@Value("${myconfig.exchangename}")
    private String exchangeName;
	
	@Value("${myconfig.routingkey}")
	private String routingKey;
    
    /**
     * rabbitMQ Queue 설정.
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    /**
     * rabbitMQ Exchange 설정.
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * bean으로 등록한 Queue와 Exchange를 Binding하면서 Exchange에서 사용될 패턴을 설정.
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * RabbitTemplate는 Spring boot에서 자동으로 빈 등록을 해주지만, 
     * 받은 메시지 처리를 위한 messageConverter를 설정하기 위해 등록.
     * @param connectionFactory
     * @param messageConverter
     * @return
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
