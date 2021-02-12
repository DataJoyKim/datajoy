package com.d1.ws.controller.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.d1.ws.controller.UserController;
import com.d1.ws.service.dto.UserDTO;

public class UserResource extends ResourceSupport {
	private Object content;
	
	public UserResource(UserDTO user) {
		this.content = user;
		this.add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getId()).withSelfRel());
	}
	
	public Object getContent() {
		return this.content;
	}
}
