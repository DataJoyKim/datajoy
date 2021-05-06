package com.d1.auth.domain;

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
@Table(name = "user")
public class Account {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "group_cd")
	private String groupCd;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "user_nm")
	private String userNm;

	@Column(name = "email")
	private String email;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "sta_date")
	private LocalDateTime staDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}
}
