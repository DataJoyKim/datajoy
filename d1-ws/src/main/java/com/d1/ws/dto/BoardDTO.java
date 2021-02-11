package com.d1.ws.dto;

import org.modelmapper.ModelMapper;

import com.d1.ws.code.BoardStatus;
import com.d1.ws.domain.Board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(of = "id")
public class BoardDTO {
	private Long id;
	private BoardStatus status;
	private String title;
	private String content;
	
	public BoardDTO(Board board) {
		ModelMapper mapper = new ModelMapper();
		mapper.map(board, this);
	}
}
