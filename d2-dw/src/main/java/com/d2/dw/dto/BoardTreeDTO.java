package com.d2.dw.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardTreeDTO {
	private Long id;
	private BoardStatus status;
	private String title;
	private String content;
	
	private List<BoardTreeDTO> childList = new ArrayList<>();	
	
	public static BoardTreeDTO convert(Board board) {
		if(board == null) return null;
		
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(Board.class, BoardTreeDTO.class)
				.addMappings(mapping -> mapping.skip(BoardTreeDTO::setChildList));
		
		BoardTreeDTO boardTreeDTO = mapper.map(board, BoardTreeDTO.class);
		boardTreeDTO.setChildList(board.getChildList().stream()
											.map(o -> BoardTreeDTO.convert(o))
											.collect(Collectors.toList()));
		
		return boardTreeDTO;
	}
}
