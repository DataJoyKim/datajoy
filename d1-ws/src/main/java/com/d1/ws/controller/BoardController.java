package com.d1.ws.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.ws.domain.Project;
import com.d1.ws.dto.BoardDTO;
import com.d1.ws.dto.BoardTreeDTO;
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
	public ResponseEntity<?> getBoard(@PathVariable Long projectId, @PathVariable Long boardId, @RequestParam Map<String, String> params) {
		Project project = projectService.getProject(projectId);
		if(project == null) {
			//throw exception
		}
		
		BoardDTO board = new BoardDTO(boardService.getBoard(boardId));
		
		//BoardResource boardResource = new BoardResource(board);
		//boardResource.add(ControllerLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	@GetMapping("/tree")
	public ResponseEntity<?> getBoardsTree(@PathVariable Long projectId, @RequestParam Map<String, String> params) {
		Project project = projectService.getProject(projectId);
		if(project == null) {
			//throw exception
		}
		
		List<BoardTreeDTO> boards = boardService.getBoardsTree(project, params)
													.stream()
													.map(o -> new BoardTreeDTO(o))
													.collect(Collectors.toList());
		
		return new ResponseEntity<>(boards, HttpStatus.OK);
	}
}
