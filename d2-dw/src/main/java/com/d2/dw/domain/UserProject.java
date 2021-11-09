package com.d2.dw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.d2.dw.code.UserLevel;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode(of = "id")
public class UserProject {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INTEGER")
	private Long id;
	
	private User user;
	
	private Project project;
	
	private UserLevel userLevel;
}
