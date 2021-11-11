package com.d2.dw.controller;

import java.security.Principal;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.d2.dw.dto.BoardDTO.BoardResponse;
import com.d2.dw.dto.BoardDTO.BoardWriteRequest;
import com.d2.dw.error.BoardErrorCode;
import com.d2.dw.exception.BusinessException;
import com.d2.dw.resource.BoardResource;
import com.d2.dw.service.BoardService;
import com.d2.dw.service.ProjectService;
import com.d2.dw.service.query.BoardQueryService;

import lombok.RequiredArgsConstructor;


@EnableCaching
@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final BoardQueryService boardQueryService;
	private final ProjectService projectService;

	@GetMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> getBoard(
			@PathVariable Long projectId
			, @PathVariable Long boardId
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
		
		BoardResponse board = boardQueryService.getBoard(boardId);
		
		BoardResource resource = new BoardResource(board);
		resource.add(WebMvcLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/projects/{projectId}/boards")
	public ResponseEntity<?> getBoards(
			@PathVariable Long projectId
			, @RequestParam("query") String query
			, Pageable pageable
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
		
		Page<BoardResponse> boards = boardQueryService.findBoards(project, query, pageable);
		BoardResource resource = new BoardResource(boards);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/projects/{projectId}/temp-boards")
	public ResponseEntity<?> writeTempBoard(
			Principal principal
			, @PathVariable Long projectId
			, @RequestBody BoardWriteRequest params
			) {
		
		BoardResponse savedBoard = boardQueryService.writeTempBoard(principal.getName(), projectId, params);
		BoardResource resource = new BoardResource(savedBoard);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PutMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> putBoard(
			@PathVariable Long projectId
			, @PathVariable Long boardId
			, @RequestBody BoardDTO params
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}

		Board board = boardService.getBoardById(boardId);
		
		boardQueryService.deleteBoard(null);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> deleteBoard(
			@PathVariable Long projectId
			, @PathVariable Long boardId
			, @RequestBody BoardDTO params
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
		
		BoardDTO.BoardResponse boardDto = boardQueryService.getBoard(boardId);
		
		//Board board = boardQueryService.saveBoard(null);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
