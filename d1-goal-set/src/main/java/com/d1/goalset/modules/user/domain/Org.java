package com.d1.goalset.modules.user.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Getter
public class Org {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_id")
	private Long id;
	
	@Column(name = "org_cd")
	private String orgCd;
	
	@Column(name = "org_nm")
	private String orgNm;
}
