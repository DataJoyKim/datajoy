package com.d1.ws.domain.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.d1.ws.controller.UserController;
import com.d1.ws.domain.User;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class UserResource extends ResourceSupport {
	
	@JsonUnwrapped
	private User user;
	
	public UserResource(User user) {
		this.user = user;
		this.add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getId()).withSelfRel());
	}
	
	public User getUser() {
		return this.user;
	}
}
