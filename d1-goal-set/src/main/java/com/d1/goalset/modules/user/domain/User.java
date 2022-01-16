package com.d1.goalset.modules.user.domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass 
@Getter 
@AllArgsConstructor @NoArgsConstructor
public abstract class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "season_cd")
	private String seasonCd;
	
	@Column(name = "company_cd")
	private String companyCd;
	
	@Column(name = "emp_no")
	private String empNo;
	
	@Column(name = "emp_nm")
	private String empNm;
	
	@Column(name = "primary_account_flag")
	private Boolean isPrimaryAccount;
	
	@Column(name = "duty_cd")
	private String dutyCd;
	
	@Column(name = "position_cd")
	private String positionCd;
	
	@Column(name = "job_cd")
	private String jobCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private Org org;
	
	@Column(name = "use_flag")
	private Boolean isUse;
}
