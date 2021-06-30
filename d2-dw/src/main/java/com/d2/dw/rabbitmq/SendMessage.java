package com.d2.dw.rabbitmq;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SendMessage {
	
	private String domain;
	
	private Integer port;
	
	private String requestPath;
	
	private String remoteIp;
	
	private String remoteHost;
	
	private String requestBody;
	
	private String userId;
}
