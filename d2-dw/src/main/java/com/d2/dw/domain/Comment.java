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
@Table(name = "comment")
public class Comment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_no")
	private Long id;
	
	@Lob
	@Column(name = "comment")
	private String comment;
}
