package com.d1.ws.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.d1.ws.code.BoardStatus;
import com.d1.ws.domain.Board;
import com.d1.ws.domain.EntityCreateUpdateData;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(of = "id")
public class BoardTreeDTO {
	private Long id;
	private BoardStatus status;
	private String title;
	private String content;
	private Board parent;
	private List<Board> childList = new ArrayList<>();	
	private EntityCreateUpdateData entityCreateUpdateData;
	
	public BoardTreeDTO(Board board) {
		ModelMapper mapper = new ModelMapper();
		mapper.map(board, this);
	}
}
