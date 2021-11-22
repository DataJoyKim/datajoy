package com.d2.dw.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.d2.dw.dto.CommentDTO.CommentWriteRequestDTO;
import com.d2.dw.validator.CommentValidator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @EqualsAndHashCode(of = "id") @NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reg_user_id")
	private User user;
	
	@Embedded
	private EntityCreateUpdateData entityCreateUpdateData;

	/**
	 * 생성 메서드
	 * 코멘트 작성
	 * @param commentValidator
	 * @param writer
	 * @param project
	 * @param board
	 * @return
	 */
	public static Comment writeComment(CommentValidator commentValidator, User writer, Project project, Board board
			, CommentWriteRequestDTO params) {
		
		commentValidator.validateWriteComment(writer, project, board, params);
		
		Comment comment = Comment.builder()
								.board(board)
								.comment(params.getComment())
								.user(writer)
								.build();
		
		return comment;
	}
	
	/**
	 * 코멘트 수정
	 * @param commentValidator
	 * @param writer
	 * @param project
	 * @param board
	 * @return
	 */
	public void updateComment(CommentValidator commentValidator, User writer, Project project, Board board
			, CommentWriteRequestDTO params) {
		
		commentValidator.validateUpdateComment(writer, project, board, this, params);

		this.user = writer;
		this.board = board;
	}

	/**
	 * 코멘트 삭제
	 * @param commentValidator
	 * @param writer
	 * @param project
	 * @param board
	 * @return
	 */
	public void deleteComment(CommentValidator commentValidator, User writer, Project project, Board board) {
		commentValidator.validateDeleteComment(writer, project, board, this);
	}
}
