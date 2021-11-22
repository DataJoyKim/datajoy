package com.d2.dw.dto;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.d2.dw.domain.Comment;
import com.d2.dw.domain.EntityCreateUpdateData;

import lombok.Builder;
import lombok.Getter;


public class CommentDTO {

	@Getter @Builder
	public static class CommentResponseDTO {
		private Long id;
		
		private String comment;
		
		private BoardDTO board;
		
		private EntityCreateUpdateData entityCreateUpdateData;
		
		public static CommentResponseDTO of(Comment comment) {
			if(comment == null) return null;
			
			ModelMapper mapper = new ModelMapper();
			mapper.createTypeMap(Comment.class, CommentResponseDTO.class);
			
			CommentResponseDTO commentResponseDTO = mapper.map(comment, CommentResponseDTO.class);
			
			return commentResponseDTO;
		}
	
		public static Page<CommentResponseDTO> of(Page<Comment> comments) {
			return comments.map(o -> CommentResponseDTO.of(o));
		}
	}
	
	@Getter @Builder
	public static class CommentWriteRequestDTO {
		private String comment;
	}
}
