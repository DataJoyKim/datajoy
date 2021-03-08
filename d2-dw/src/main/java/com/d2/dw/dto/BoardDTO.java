package com.d2.dw.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDTO {
	
	private Long id;
	
	private BoardStatus status;
	
	private String title;
	
	private String content;
	
	private UserDTO user;
	
	private ProjectDTO project;
	
	public static BoardDTO convert(Board board) {
		if(board == null) return null;
		
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(Board.class, BoardDTO.class)
				.addMappings(mapping -> mapping.skip(BoardDTO::setUser))
				.addMappings(mapping -> mapping.skip(BoardDTO::setProject));
		BoardDTO boardDto = mapper.map(board, BoardDTO.class);
		
		boardDto.setUser(UserDTO.convert(board.getUser()));
		boardDto.setProject(ProjectDTO.convert(board.getProject()));
		
		return boardDto;
	}

	public static List<BoardTreeDTO> convert(List<Board> boards) {
		return boards.stream()
				.map(o -> BoardTreeDTO.convert(o))
				.collect(Collectors.toList());
	}
}
