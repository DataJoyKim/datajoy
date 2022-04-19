package com.d1.apigateway.security;

import java.time.LocalDateTime;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor @Builder
public class Account {

	private String id;
	
	private String empId;
	
	private String companyCd;
	
	private String password;
	
	private String empNo;
	
	private String empNm;

	private String email;
	
	private LocalDateTime hireDate;
	
	private LocalDateTime retireDate;

	public static Account createAccount(Claims body) {
		return Account.builder()
					.id(body.getSubject())
					.empId((String) body.get("empId"))
					.companyCd((String) body.get("companyCd"))
					.password((String) body.get("password"))
					.empNo((String) body.get("empNo"))
					.empNm((String) body.get("empNm"))
					.email((String) body.get("email"))
					.build();
	}
}
