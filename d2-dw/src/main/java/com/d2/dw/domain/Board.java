package com.d2.dw.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

import com.d2.dw.code.BoardStatus;
import com.d2.dw.dto.BoardDTO.SaveBoardRequest;
import com.d2.dw.validator.BoardValidator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @EqualsAndHashCode(of = "id") @NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder 
@Entity
@Table(name = "board")
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_no")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private BoardStatus status;
	
	@Column(name = "title", columnDefinition = "TEXT")
	private String title;
	
	@Lob
	@Column(name = "content")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reg_user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_no")
	private Project project;
	
	@Embedded
	private EntityCreateUpdateData entityCreateUpdateData;

	public static Board write(BoardValidator boardValidator, User writer, Project project, SaveBoardRequest params) {
		boardValidator.validateWrite(project, params);
		
		Board board = Board.builder()
							.project(project)
							.content(params.getContent())
							.title(params.getTitle())
							.user(writer)
							.status(BoardStatus.SAVE)
							.entityCreateUpdateData(EntityCreateUpdateData.createNowDate())
							.build();
		
		return board;
	}
}
