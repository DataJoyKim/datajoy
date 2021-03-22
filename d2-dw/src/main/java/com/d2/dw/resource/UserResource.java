package com.d2.dw.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.d2.dw.controller.CommentController;
import com.d2.dw.dto.UserDTO;

public class UserResource extends RepresentationModel<UserResource> {
	private Object content;
	
	public UserResource(UserDTO user) {
		this.content = user;
		this.add(WebMvcLinkBuilder.linkTo(CommentController.class).withSelfRel());
	}
	
	public Object getContent() {
		return this.content;
	}
}
