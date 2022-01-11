package com.d1.goalset.modules.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	
	private String empNo;
	
	private String name;
	
	private Boolean isPrimaryAccount;
	
	private String dutyCd;
	
	private Org org;
}
