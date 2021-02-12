package com.d1.ws.controller.resource;

import org.springframework.hateoas.ResourceSupport;

import com.d1.ws.service.dto.CommentDTO;

import lombok.Getter;

@Getter
public class CommentResource extends ResourceSupport {
	private Object content;
	
	public CommentResource(CommentDTO comment) {
		this.content = comment;
		
	}
}
