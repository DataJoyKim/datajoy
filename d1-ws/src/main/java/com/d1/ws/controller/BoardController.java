package com.d1.ws.controller;

import java.util.List;
import java.util.Map;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.ws.controller.resource.BoardResource;
import com.d1.ws.domain.Project;
import com.d1.ws.service.BoardService;
import com.d1.ws.service.ProjectService;
import com.d1.ws.service.dto.BoardDTO;
import com.d1.ws.service.dto.BoardTreeDTO;
import com.d1.ws.service.query.BoardQueryService;
import com.d1.ws.validator.BoardValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(produces = "application/hal+json")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	private final BoardQueryService boardQueryService;
	
	private final ProjectService projectService;
	
	private final BoardValidator boardValidator;

	@GetMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> getBoard(@PathVariable Long projectId, @PathVariable Long boardId, @RequestParam Map<String, String> params) {
		Project project = projectService.getProject(projectId);
		if(project == null) {
			//throw exception
		}
		
		BoardDTO board = boardQueryService.findBoard(boardId);
		
		BoardResource resource = new BoardResource(board);
		resource.add(ControllerLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/projects/{projectId}/boards/tree")
	public ResponseEntity<?> getBoardsTree(@PathVariable Long projectId, @RequestParam Map<String, String> params, Errors errors) {
		Project project = projectService.getProject(projectId);
		
		boardValidator.validate(project, errors);
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		
		List<BoardTreeDTO> boards = boardQueryService.findBoardsTree(project, params);
		BoardResource resource = new BoardResource(boards);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
}
