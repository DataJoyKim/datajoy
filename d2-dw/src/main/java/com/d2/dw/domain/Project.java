package com.d2.dw.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "project")
public class Project {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_no")
	private Long id;
	
	@Column(name = "project_nm")
	private String projectNm;
	
	@Lob
	@Column(name = "description")
	private String description;

	@Embedded
	private EntityCreateUpdateData entityCreateUpdateData;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reg_user_id")
	private User user;
}
