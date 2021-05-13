package com.d2.dw.controller;

import java.util.List;
import java.util.Map;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO;
import com.d2.dw.dto.BoardTreeDTO;
import com.d2.dw.resource.BoardResource;
import com.d2.dw.service.ProjectService;
import com.d2.dw.service.query.BoardQueryService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class BoardController {
	
	//private final BoardService boardService;
	
	private final BoardQueryService boardQueryService;
	
	private final ProjectService projectService;
	
	//private final BoardValidator boardValidator;

	@GetMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> getBoard(@PathVariable Long projectId, @PathVariable Long boardId, @RequestParam Map<String, String> params) {
		Project project = projectService.findProject(projectId);
		if(project == null) {
			//throw exception
		}
		
		BoardDTO board = boardQueryService.getBoard(boardId);
		
		BoardResource resource = new BoardResource(board);
		resource.add(WebMvcLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/projects/{projectId}/boards/tree")
	public ResponseEntity<?> getBoardsTree(@PathVariable Long projectId, @RequestParam Map<String, String> params, Errors errors) {
		Project project = projectService.findProject(projectId);
		/*
		boardValidator.validate(project, errors);
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		*/
		
		List<BoardTreeDTO> boards = boardQueryService.getBoardsTree(project, params);
		BoardResource resource = new BoardResource(boards);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> saveBoardsTree(@PathVariable Long projectId, @PathVariable Long boardId, @RequestBody Map<String, String> params) {
		Project project = projectService.findProject(projectId);
		if(project == null) {
			//throw exception
		}
		
		BoardDTO board = boardQueryService.getBoard(boardId);
		
		BoardResource resource = new BoardResource(board);
		resource.add(WebMvcLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
}
