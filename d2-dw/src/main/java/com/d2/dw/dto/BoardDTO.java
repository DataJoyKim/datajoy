package com.d2.dw.dto;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDTO {
	@Getter @Setter
	public static class BoardRequest {
	}
	
	@Getter @Setter
	public static class BoardResponse {
		private Long id;
		
		private BoardStatus status;
		
		private String title;
		
		private String content;
		
		private UserDTO user;
		
		private ProjectDTO project;
		
		public static BoardResponse convert(Board board) {
			if(board == null) return null;
			
			ModelMapper mapper = new ModelMapper();
			
			//Object Skip convert setting
			mapper.createTypeMap(Board.class, BoardResponse.class)
					.addMappings(mapping -> mapping.skip(BoardResponse::setUser))
					.addMappings(mapping -> mapping.skip(BoardResponse::setProject));
			
			BoardResponse boardDto = mapper.map(board, BoardResponse.class);
			
			boardDto.setUser(UserDTO.convert(board.getUser()));
			boardDto.setProject(ProjectDTO.convert(board.getProject()));
			
			return boardDto;
		}

		public static Page<BoardResponse> convert(Page<Board> findAll) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	@Getter
	public static class BoardWriteRequest {
		
		private String content;
		
		private String title;
		
	}
}
