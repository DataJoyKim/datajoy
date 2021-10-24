package com.d2.dw.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "user")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INTEGER")
	private Long id;
	
	@Column(name = "group_cd")
	private String groupCd;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "user_nm")
	private String userNm;
	
	@Column(name = "resident_no")
	private String residentNo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "location")
	private String location;
	
	@Lob
	@Column(name = "bio")
	private String bio;
	
	@Lob
	@Column(name = "profile_image")
	private Byte[] profileImage;
	
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name = "sta_date")
	private LocalDateTime staDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Embedded
	private EntityCreateUpdateData entityCreateUpdateData;
}
