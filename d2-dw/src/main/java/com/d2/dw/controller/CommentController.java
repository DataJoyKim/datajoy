package com.d2.dw.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
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
import com.d2.dw.service.CommentService;
import com.d2.dw.service.query.CommentQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class CommentController {
	
	private final CommentService commentService;
	private final CommentQueryService commentQueryService;
	
	@GetMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments")
	public ResponseEntity<?> getComments(
			@PathVariable Long projectId
			, @PathVariable Long boardId
			, Pageable pageable
			, PagedResourcesAssembler<CommentResponseDTO> assembler
			) {
		
		Page<CommentResponseDTO> comments = commentQueryService.findCommentsOfBoard(projectId, boardId, pageable);
		 
		return new ResponseEntity<>(assembler.toModel(comments), HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments")
	public ResponseEntity<?> writeComments(
			Principal principal
			, @PathVariable Long projectId
			, @PathVariable Long boardId
			, @RequestBody CommentWriteRequestDTO params
			) {
		
		CommentResponseDTO comment = commentService.writeComment(principal.getName(), projectId, boardId, params);
		
		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}
	
	@PutMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments/{commentId}")
	public ResponseEntity<?> putComments(
			Principal principal
			, @PathVariable Long projectId
			, @PathVariable Long boardId
			, @PathVariable Long commentId
			, @RequestBody CommentWriteRequestDTO params
			) {
		
		CommentResponseDTO comment = commentService.updateComment(principal.getName(), projectId, boardId, commentId, params);
		
		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}

	@DeleteMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments/{commentId}")
	public ResponseEntity<?> deleteComments(
			Principal principal
			, @PathVariable Long projectId
			, @PathVariable Long boardId
			, @PathVariable Long commentId
			) {
		
		commentService.deleteComment(principal.getName(), projectId, boardId, commentId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
