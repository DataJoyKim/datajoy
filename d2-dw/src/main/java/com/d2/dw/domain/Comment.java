package com.d2.dw.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "comment")
public class Comment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_no")
	private Long id;
	
	@Lob
	@Column(name = "comment")
	private String comment;

	@ManyToOne
	@JoinColumn(name = "board_no")
	private Board board;

	@Embedded
	private EntityCreateUpdateData entityCreateUpdateData;
}
