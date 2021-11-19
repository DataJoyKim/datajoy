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
import com.d2.dw.dto.BoardDTO.BoardWriteRequestDTO;
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

	/**
	 * 생성자 메서드
	 * 임시 게시글 작성 시 사용
	 * @param boardValidator
	 * @param writer
	 * @param project
	 * @param params
	 * @return
	 */
	public static Board writeTempBoard(BoardValidator boardValidator, User writer, Project project, BoardWriteRequestDTO params) {
		boardValidator.validateWriteTempBoard(writer, project, params);
		
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

	/**
	 * 생성자 메서드
	 * 게시글 포스팅 시 사용
	 * @param boardValidator
	 * @param writer
	 * @param project
	 * @param params
	 * @return
	 */
	public static Board postingBoard(BoardValidator boardValidator, User writer, Project project, BoardWriteRequestDTO params) {
		boardValidator.validatePostBoard(writer, project, params);
		
		Board board = Board.builder()
							.project(project)
							.content(params.getContent())
							.title(params.getTitle())
							.user(writer)
							.status(BoardStatus.POSTING)
							.entityCreateUpdateData(EntityCreateUpdateData.createNowDate())
							.build();
		
		return board;
	}

	/**
	 * 임시 게시글 수정
	 * @param boardValidator
	 * @param writer
	 * @param project
	 * @param params
	 */
	public void updateTempBoard(BoardValidator boardValidator, User writer, Project project, BoardWriteRequestDTO params) {
		boardValidator.validateUpdateTempBoard(writer, project, params);
		
		this.content = params.getContent();
		this.title = params.getTitle();
	}

	/**
	 * 임시 게시글 삭제
	 * @param boardValidator
	 * @param writer
	 * @param project
	 * @param params
	 */
	public void deleteTempBoard(BoardValidator boardValidator, User writer, Project project) {
		boardValidator.validateDeleteTempBoard(writer, project);
	}

	/**
	 * 게시글 수정
	 * @param boardValidator
	 * @param writer
	 * @param project
	 * @param params
	 */
	public void updateBoard(BoardValidator boardValidator, User writer, Project project, BoardWriteRequestDTO params) {
		boardValidator.validateUpdateBoard(writer, project, params);
		
		this.content = params.getContent();
		this.title = params.getTitle();
	}

	/**
	 * 게시글 삭제
	 * @param boardValidator
	 * @param writer
	 * @param project
	 */
	public void deleteBoard(BoardValidator boardValidator, User writer, Project project) {
		boardValidator.validateDeleteBoard(writer, project);
	}
}
