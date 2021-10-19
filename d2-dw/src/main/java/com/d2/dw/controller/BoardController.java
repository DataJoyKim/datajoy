package com.d2.dw.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO;
import com.d2.dw.dto.BoardTreeDTO;
import com.d2.dw.error.BoardErrorCode;
import com.d2.dw.resource.BoardResource;
import com.d2.dw.service.ProjectService;
import com.d2.dw.service.query.BoardQueryService;

import lombok.RequiredArgsConstructor;


@EnableCaching
@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class BoardController {
	
	//private final BoardService boardService;
	
	private final BoardQueryService boardQueryService;
	
	private final ProjectService projectService;
	
	//private final BoardValidator boardValidator;

	@GetMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> getBoard(
			Principal principal
			, @PathVariable Long projectId
			, @PathVariable Long boardId
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			return new ResponseEntity<>(BoardErrorCode.NOT_FOUND_PROJECT, HttpStatus.NOT_FOUND);
		}
		
		BoardDTO.BoardResponse board = boardQueryService.getBoard(boardId);
		
		BoardResource resource = new BoardResource(board);
		resource.add(WebMvcLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/projects/{projectId}/boards/tree")
	public ResponseEntity<?> getBoardsTree(
			@PathVariable Long projectId
			, @RequestParam Map<String, String> params
			) {
		
		Project project = projectService.findProject(projectId);
		/*
		boardValidator.validate(project, errors);
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		*/
		long t1 = System.currentTimeMillis();
		List<BoardTreeDTO.BoardTreeResponse> boards = boardQueryService.getBoardsTree(project, params);
		BoardResource resource = new BoardResource(boards);
		long t2 = System.currentTimeMillis();
		
		System.out.println("수행시간 : " + String.valueOf(t2 - t1));
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> saveBoardsTree(
			@PathVariable Long projectId
			, @PathVariable Long boardId
			, @RequestBody BoardDTO params
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			//throw exception
		}
		BoardDTO.BoardResponse boardDto = boardQueryService.getBoard(boardId);
		
		Board board = boardQueryService.saveBoard(null);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> deleteBoardsTree(
			@PathVariable Long projectId
			, @PathVariable Long boardId
			, @RequestBody BoardDTO params
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			//throw exception
		}
		BoardDTO.BoardResponse boardDto = boardQueryService.getBoard(boardId);
		
		Board board = boardQueryService.saveBoard(null);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> putBoardsTree(
			@PathVariable Long projectId
			, @PathVariable Long boardId
			, @RequestBody BoardDTO params
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			//throw exception
		}
		BoardDTO.BoardResponse boardDto = boardQueryService.getBoard(boardId);
		
		boardQueryService.deleteBoard(null);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
