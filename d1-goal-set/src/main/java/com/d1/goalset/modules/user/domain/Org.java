package com.d1.goalset.modules.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "org")
@Getter
public class Org {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_id")
	private Long id;
	
	@Column(name = "org_cd")
	private String orgCd;
	
	@Column(name = "org_nm")
	private String orgNm;
	
	@Column(name = "parent_org_id")
	private Long parentOrgId;

	public static List<Long> createBatchIds(List<User> users) {
		List<Long> ids = new ArrayList<>();
		for(User user : users) {
			ids.add(user.getOrg().getId());
		}
		return ids;
	}
}
