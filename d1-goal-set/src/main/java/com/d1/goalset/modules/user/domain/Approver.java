package com.d1.goalset.modules.user.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "user")
public class Approver extends User {

	public Approver() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Builder
	public Approver(Long id, String seasonCd, String companyCd, String empNo, String empNm, Boolean isPrimaryAccount,
			String dutyCd, String positionCd, String jobCd, Org org, Boolean isUse) {
		super(id, seasonCd, companyCd, empNo, empNm, isPrimaryAccount, dutyCd, positionCd, jobCd, org, isUse);
		// TODO Auto-generated constructor stub
	}

}
