package com.d1.goalset.modules.goal.api.resource;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class GoalResource extends RepresentationModel<GoalResource> {
	
	@JsonUnwrapped
	private Object content;
	
	public GoalResource(Object content) {
		this.content = content;
	}

	public Object getContent() {
		return this.content;
	}
}
