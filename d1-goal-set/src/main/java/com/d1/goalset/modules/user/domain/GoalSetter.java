package com.d1.goalset.modules.user.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "user")
public class GoalSetter extends User {

	@Builder
	public GoalSetter(Long id, String empNo, String empNm, Boolean isPrimaryAccount, String dutyCd, String positionCd,
			String jobCd, Org org) {
		super(id, empNo, empNm, isPrimaryAccount, dutyCd, positionCd, jobCd, org);
	}



}
