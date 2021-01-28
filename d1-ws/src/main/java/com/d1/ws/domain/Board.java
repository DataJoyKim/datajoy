package com.d1.ws.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	@Id @GeneratedValue
	@Column(name = "board_no")
	private long id;
	
	@Column(name = "parent_board_no")
	private long parentBoardNo;
	
	@Column(name = "status", columnDefinition="VARCHAR")
	private BoardStatus status;
	
	@Column(name = "title", columnDefinition="TEXT")
	private String title;
	
	@Column(name = "content", columnDefinition="BLOB")
	private String content;
	
	@Column(name = "reg_date", columnDefinition="DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
    private LocalDateTime regDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reg_user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_no")
	private Project project;
}
