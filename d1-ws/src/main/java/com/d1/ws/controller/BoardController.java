package com.d1.ws.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.ws.domain.Board;
import com.d1.ws.domain.Project;
import com.d1.ws.domain.resource.BoardResource;
import com.d1.ws.service.BoardService;
import com.d1.ws.service.ProjectService;

@RestController
@RequestMapping(value = "/api/projects/{projectId}/boards", produces = "application/hal+json")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private ProjectService projectService;

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardResource> getBoard(@PathVariable Long projectId, @PathVariable Long boardId, @RequestParam Map<String, String> params) {
		
		Board board = boardService.getBoard(boardId);
		
		BoardResource boardResource = new BoardResource(board);
		boardResource.add(ControllerLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return ResponseEntity.ok(boardResource);
	}
	

	@GetMapping
	public ResponseEntity<List<Board>> getBoardsProject(@PathVariable Long projectId, @RequestParam Map<String, String> params) {
		Project project = projectService.getProject(projectId);
		
		List<Board> board = boardService.getProjectBoards(project, params);
		
		return ResponseEntity.ok(board);
	}
}
