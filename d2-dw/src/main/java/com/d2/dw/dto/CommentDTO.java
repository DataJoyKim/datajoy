package com.d2.dw.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.d2.dw.domain.Comment;
import com.d2.dw.domain.EntityCreateUpdateData;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDTO {
	
	private Long id;
	
	private String comment;
	
	private BoardDTO board;
	
	private List<CommentDTO> childList = new ArrayList<>();
	
	private EntityCreateUpdateData entityCreateUpdateData;
	
	public static CommentDTO convert(Comment comment) {
		if(comment == null) return null;
		
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(Comment.class, CommentDTO.class)
				.addMappings(m -> m.skip(CommentDTO::setChildList));
		
		CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
		commentDTO.setChildList(comment.getChildList()
										.stream()
										.map(o -> CommentDTO.convert(o))
										.collect(Collectors.toList()));
		
		return commentDTO;
	}

	public static Page<CommentDTO> convert(Page<Comment> comments) {
		return comments.map(o -> CommentDTO.convert(o));
	}
}
