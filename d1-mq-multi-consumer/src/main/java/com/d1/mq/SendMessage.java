package com.d1.mq;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SendMessage {
	
	private String domain;
	
	private Integer port;
	
	private String requestPath;
	
	private String remoteIp;
	
	private String remoteHost;
	
	private String requestBody; 
	
	private String userId;
}
