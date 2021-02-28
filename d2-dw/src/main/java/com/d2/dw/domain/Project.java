package com.d2.dw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(of = "id")
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
}
