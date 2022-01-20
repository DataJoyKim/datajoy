package com.d1.goalset.modules.goal.api.resource;

import org.springframework.hateoas.RepresentationModel;

public class GoalResource extends RepresentationModel<GoalResource> {

	private Object content;
	
	public GoalResource(Object content) {
		this.content = content;
	}

	public Object getContent() {
		return this.content;
	}
}
