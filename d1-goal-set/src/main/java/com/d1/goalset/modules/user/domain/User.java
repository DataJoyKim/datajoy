package com.d1.goalset.modules.user.domain;

import javax.persistence.Entity;

import lombok.Getter;

@Getter
@Entity
public class User {
	private String userId;
	private String empNo;
	private String name;
	private Boolean isPrimaryAccount;
	private String dutyCd;
	private Org org;
}
