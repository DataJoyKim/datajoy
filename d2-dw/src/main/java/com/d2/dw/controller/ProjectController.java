package com.d2.dw.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d2.dw.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class ProjectController {
	
	private final ProjectService projectService;
	
	public ResponseEntity<?> getProject() {
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
