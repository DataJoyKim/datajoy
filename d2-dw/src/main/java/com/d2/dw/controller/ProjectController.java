package com.d2.dw.controller;

import java.util.List;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d2.dw.dto.ProjectDTO;
import com.d2.dw.resource.ProjectResource;
import com.d2.dw.service.query.ProjectQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class ProjectController {
	
	private final ProjectQueryService projectQueryService;
	
	@GetMapping("/api/v1/projects")
	public ResponseEntity<?> getProjects() {
		List<ProjectDTO> projects = projectQueryService.getMyProjects(null);
		
		ProjectResource resource = new ProjectResource(projects);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
}
