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
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@ConfigurationProperties("spring.rabbitmq.server01")
public class RabbitConfigurationServer01 extends RabbitConfiguration {

    @Bean(name = "connectionFactoryServer01")
    @Primary
    public ConnectionFactory connectionFactory() {
        return super.connectionFactory();
    }

    @Bean(name = "rabbitTemplateServer01")
    @Primary
    public RabbitTemplate rabbitTemplate(@Qualifier("connectionFactoryServer01") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name = "factoryServer01")
    public SimpleRabbitListenerContainerFactory factory(SimpleRabbitListenerContainerFactoryConfigurer configurer, @Qualifier("connectionFactoryServer01") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(value = "rabbitAdminServer01")
    public RabbitAdmin rabbitAdmin(@Qualifier("connectionFactoryServer01") ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
