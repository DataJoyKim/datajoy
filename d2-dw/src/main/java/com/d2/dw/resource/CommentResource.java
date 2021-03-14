package com.d2.dw.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.d2.dw.controller.CommentController;
import com.d2.dw.dto.CommentDTO;

public class CommentResource extends RepresentationModel<CommentResource> {
	private Object content;
	
	public CommentResource(CommentDTO comment) {
		this.content = comment;
		this.add(WebMvcLinkBuilder.linkTo(CommentController.class).withSelfRel());
	}
	
	public Object getContent() {
		return this.content;
	}

}
