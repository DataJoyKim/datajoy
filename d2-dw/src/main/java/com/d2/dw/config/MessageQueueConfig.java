package com.d2.dw.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.d2.dw.rabbitmq.MessageQueuePolicy;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MessageQueueConfig {
	
	private final MessageQueuePolicy messageQueuePolicy;
    
    /**
     * rabbitMQ Queue 설정.
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(messageQueuePolicy.getQueueName(), true);
    }

    /**
     * rabbitMQ Exchange 설정.
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(messageQueuePolicy.getExchangeName(), true, false);
    }

    /**
     * bean으로 등록한 Queue와 Exchange를 Binding하면서 Exchange에서 사용될 패턴을 설정.
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(messageQueuePolicy.getRoutingKey());
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

    /**
     * Object 메시지를 Json 타입으로 변환
     * @return
     */
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
