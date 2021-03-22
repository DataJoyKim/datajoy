package com.d2.dw.resource;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.d2.dw.controller.CommentController;
import com.d2.dw.dto.ProjectDTO;

public class ProjectResource extends RepresentationModel<ProjectResource> {
	private Object content;
	
	public ProjectResource(ProjectDTO project) {
		this.content = project;
		this.add(WebMvcLinkBuilder.linkTo(CommentController.class).withSelfRel());
	}
	
	public ProjectResource(List<ProjectDTO> projects) {
		this.content = projects;
		this.add(WebMvcLinkBuilder.linkTo(CommentController.class).withSelfRel());
	}

	public Object getContent() {
		return this.content;
	}
}
