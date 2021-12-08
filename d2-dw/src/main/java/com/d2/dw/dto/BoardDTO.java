package com.d2.dw.dto;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;
import com.d2.dw.dto.ProjectDTO.ProjectResponseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDTO {
	@Getter @Setter
	public static class BoardRequestDTO {
	}
	
	@Getter @Setter
	public static class BoardResponseDTO {
		private Long id;
		
		private BoardStatus status;
		
		private String title;
		
		private String content;
		
		private UserDTO user;
		
		private ProjectResponseDTO project;
		
		public static BoardResponseDTO of(Board board) {
			if(board == null) {
				return null;
			}
			
			ModelMapper mapper = new ModelMapper();
			
			//Object Skip convert setting
			mapper.createTypeMap(Board.class, BoardResponseDTO.class)
					.addMappings(mapping -> mapping.skip(BoardResponseDTO::setUser))
					.addMappings(mapping -> mapping.skip(BoardResponseDTO::setProject));
			
			BoardResponseDTO boardDto = mapper.map(board, BoardResponseDTO.class);
			
			boardDto.setUser(UserDTO.convert(board.getUser()));
			boardDto.setProject(ProjectResponseDTO.of(board.getProject()));
			
			return boardDto;
		}

		public static Page<BoardResponseDTO> of(Page<Board> boards) {
			return new PageImpl<BoardResponseDTO>(
								boards.getContent().stream().map(o -> BoardResponseDTO.of(o)).collect(Collectors.toList())
								, boards.getPageable()
								, boards.getTotalElements());
		}
	}
	
	@Getter @Builder
	public static class BoardWriteRequestDTO {
		
		private String content;
		
		private String title;
		
	}
}
