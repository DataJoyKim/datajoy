package com.d2.dw.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;
import com.d2.dw.domain.EntityCreateUpdateData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class BoardTreeDTO {

	@Getter @Setter @NoArgsConstructor @AllArgsConstructor
	public static class BoardTreeResponse {
		private Long id;
		
		private BoardStatus status;
		
		private String title;
		
		private String content;
		
		private List<BoardTreeDTO.BoardTreeResponse> childList = new ArrayList<>();	
	
		private EntityCreateUpdateData entityCreateUpdateData;
		
		public static BoardTreeDTO.BoardTreeResponse convert(Board board) {
			if(board == null) return null;
			
			ModelMapper mapper = new ModelMapper();
			mapper.createTypeMap(Board.class, BoardTreeDTO.BoardTreeResponse.class)
					.addMappings(mapping -> mapping.skip(BoardTreeDTO.BoardTreeResponse::setChildList));
			
			BoardTreeDTO.BoardTreeResponse boardTreeDTO = mapper.map(board, BoardTreeDTO.BoardTreeResponse.class);
			boardTreeDTO.setChildList(board.getChildList().stream()
												.map(o -> BoardTreeDTO.BoardTreeResponse.convert(o))
												.collect(Collectors.toList()));
			
			return boardTreeDTO;
		}
	
		public static List<BoardTreeDTO.BoardTreeResponse> convert(List<Board> boards) {
			return boards.stream()
					.map(o -> BoardTreeDTO.BoardTreeResponse.convert(o))
					.collect(Collectors.toList());
		}
	}
}
