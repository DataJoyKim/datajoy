package com.d1.auth.jwt;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "emp")
public class Account {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id", columnDefinition = "INTEGER")
	private Long id;
	
	@Column(name = "company_cd")
	private String companyCd;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "emp_no")
	private String empNo;
	
	@Column(name = "emp_nm")
	private String empNm;

	@Column(name = "email")
	private String email;
	
	@Column(name = "hire_date")
	private LocalDateTime hireDate;
	
	@Column(name = "retire_date")
	private LocalDateTime retireDate;
	
	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}
}
