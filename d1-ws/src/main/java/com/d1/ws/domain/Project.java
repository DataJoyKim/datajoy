package com.d1.ws.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
@Table(name="project")
public class Project {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_no")
	private Long id;
	
	@Column(name = "project_nm")
	private String projectNm;
	
	@Lob
	@Column(name = "description")
	private String description;

	@Column(name = "reg_date")
    private LocalDateTime regDate;
	
	@Column(name = "mod_user_id")
	private Long modUserId;
	
	@Column(name = "mod_date")
    private LocalDateTime modDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reg_user_id")
	private User user;
}
