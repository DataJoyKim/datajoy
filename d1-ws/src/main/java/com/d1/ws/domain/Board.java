package com.d1.ws.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.d1.ws.code.BoardStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
@Table(name="board")
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_no")
	private Long id;
	
	@Column(name = "parent_board_no")
	private Long parentBoardNo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private BoardStatus status;
	
	@Column(name = "title", columnDefinition = "TEXT")
	private String title;
	
	@Lob
	@Column(name = "content")
	private String content;
	
	@Column(name = "reg_date")
    private LocalDateTime regDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reg_user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_no")
	private Project project;
}
