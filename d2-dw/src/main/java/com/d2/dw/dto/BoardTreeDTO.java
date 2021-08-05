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
	public class Response {
		private Long id;
		
		private BoardStatus status;
		
		private String title;
		
		private String content;
		
		private List<BoardTreeDTO> childList = new ArrayList<>();	
	
		private EntityCreateUpdateData entityCreateUpdateData;
		
		public static BoardTreeDTO.Response convert(Board board) {
			if(board == null) return null;
			
			ModelMapper mapper = new ModelMapper();
			mapper.createTypeMap(Board.class, BoardTreeDTO.Response.class)
					.addMappings(mapping -> mapping.skip(BoardTreeDTO.Response::setChildList));
			
			BoardTreeDTO.Response boardTreeDTO = mapper.map(board, BoardTreeDTO.Response.class);
			boardTreeDTO.setChildList(board.getChildList().stream()
												.map(o -> BoardTreeDTO.Response.convert(o))
												.collect(Collectors.toList()));
			
			return boardTreeDTO;
		}
	}
}
