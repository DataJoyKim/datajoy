package com.d2.dw.controller;

import java.security.Principal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d2.dw.dto.CommentDTO.CommentResponseDTO;
import com.d2.dw.dto.CommentDTO.CommentWriteRequestDTO;
import com.d2.dw.dto.ProjectDTO.ProjectResponseDTO;
import com.d2.dw.service.query.ProjectQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class ProjectController {
	
	private final ProjectQueryService projectQueryService;

	@GetMapping("/api/v1/projects")
	public ResponseEntity<?> getProjects(
			@PathVariable Long projectId
			, Pageable pageable
			, PagedResourcesAssembler<ProjectResponseDTO> assembler
			) {
		
		 
		return new ResponseEntity<>(assembler.toModel(null), HttpStatus.OK);
	}

	@GetMapping("/api/v1/projects/{projectId}")
	public ResponseEntity<?> getProjects(
			@PathVariable Long projectId
			) {
		
		 
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/projects")
	public ResponseEntity<?> createProjects(
			Principal principal
			, @RequestBody CommentWriteRequestDTO params
			) {
		
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
	
	@PutMapping("/api/v1/projects/{projectId}")
	public ResponseEntity<?> putProject(
			Principal principal
			, @PathVariable Long projectId
			, @RequestBody CommentWriteRequestDTO params
			) {
		
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@DeleteMapping("/api/v1/projects/{projectId}")
	public ResponseEntity<?> deleteProject(
			Principal principal
			, @PathVariable Long projectId
			) {
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
