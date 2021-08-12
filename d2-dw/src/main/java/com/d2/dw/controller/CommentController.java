package com.d2.dw.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d2.dw.domain.Board;
import com.d2.dw.dto.CommentDTO;
import com.d2.dw.resource.CommentResource;
import com.d2.dw.service.BoardService;
import com.d2.dw.service.query.CommentQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class CommentController {
	
	private final BoardService boardSerive;
	
	private final CommentQueryService commentQueryService;
	
	@GetMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments")
	public ResponseEntity<?> getComments(@PathVariable Long projectId, @PathVariable Long boardId, Pageable pageable, PagedResourcesAssembler<CommentDTO> assembler) {
		Board board = boardSerive.getBoardById(boardId);
		if(board == null) {
			
		}
		
		Page<CommentDTO> comments = commentQueryService.getCommentsByBoard(board, pageable);
		
		return new ResponseEntity<>(assembler.toModel(comments, e -> new CommentResource(e)), HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments")
	public ResponseEntity<?> postComments(@PathVariable Long projectId, @PathVariable Long boardId) {
		Board board = boardSerive.getBoardById(boardId);
		if(board == null) {
			
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PutMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments")
	public ResponseEntity<?> putComments(@PathVariable Long projectId, @PathVariable Long boardId) {
		Board board = boardSerive.getBoardById(boardId);
		if(board == null) {
			
		}
		
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	
	@DeleteMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments")
	public ResponseEntity<?> deleteComments(@PathVariable Long projectId, @PathVariable Long boardId) {
		Board board = boardSerive.getBoardById(boardId);
		if(board == null) {
			
		}
		
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
