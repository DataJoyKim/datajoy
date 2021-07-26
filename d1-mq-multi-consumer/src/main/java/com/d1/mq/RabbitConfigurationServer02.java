package com.d1.mq;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@ConfigurationProperties("spring.rabbitmq.server02")
public class RabbitConfigurationServer02 extends RabbitConfiguration {

    @Bean(name = "connectionFactoryServer02")
    public ConnectionFactory connectionFactory() {
        return super.connectionFactory();
    }

    @Bean(name = "rabbitTemplateServer02")
    public RabbitTemplate rabbitTemplate(@Qualifier("connectionFactoryServer02") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name = "factoryServer02")
    public SimpleRabbitListenerContainerFactory factory(SimpleRabbitListenerContainerFactoryConfigurer configurer, @Qualifier("connectionFactoryServer02") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(value = "rabbitAdminServer02")
    public RabbitAdmin rabbitAdmin(@Qualifier("connectionFactoryServer02") ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
