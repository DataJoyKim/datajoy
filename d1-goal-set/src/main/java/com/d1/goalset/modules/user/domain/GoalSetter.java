package com.d1.goalset.modules.user.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class GoalSetter extends User {

	public GoalSetter(Long id, String empNo, String empNm, Boolean isPrimaryAccount, String dutyCd, String positionCd,
			String jobCd, Org org) {
		super(id, empNo, empNm, isPrimaryAccount, dutyCd, positionCd, jobCd, org);
		// TODO Auto-generated constructor stub
	}

}
