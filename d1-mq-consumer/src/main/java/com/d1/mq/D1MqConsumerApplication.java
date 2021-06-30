package com.d1.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class D1MqConsumerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(D1MqConsumerApplication.class, args);
	}

}
