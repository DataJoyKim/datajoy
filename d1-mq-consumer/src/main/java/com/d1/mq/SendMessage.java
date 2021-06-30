package com.d1.mq;

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
