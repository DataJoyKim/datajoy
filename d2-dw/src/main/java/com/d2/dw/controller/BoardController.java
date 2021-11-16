package com.d2.dw.controller;

import java.security.Principal;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
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

import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO.BoardResponse;
import com.d2.dw.dto.BoardDTO.BoardWriteRequest;
import com.d2.dw.error.BoardErrorCode;
import com.d2.dw.exception.BusinessException;
import com.d2.dw.resource.BoardResource;
import com.d2.dw.service.BoardService;
import com.d2.dw.service.ProjectService;

import lombok.RequiredArgsConstructor;

@EnableCaching
@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
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
		
		BoardResponse board = BoardResponse.convert(boardService.findBoard(boardId));
		
		BoardResource resource = new BoardResource(board);
		resource.add(WebMvcLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/projects/{projectId}/boards")
	public ResponseEntity<?> getBoards(
			@PathVariable Long projectId
			, @RequestParam("query") String query
			, Pageable pageable
			, PagedResourcesAssembler<BoardResponse> assembler
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
		
		Page<BoardResponse> boards = BoardResponse.convert(boardService.findBoards(query, pageable));
		
		return new ResponseEntity<>(assembler.toModel(boards, e -> new BoardResource(e)), HttpStatus.OK);
	}

	@GetMapping("/api/v1/projects/{projectId}/temp-boards/{boardId}")
	public ResponseEntity<?> getTempBoard(
			@PathVariable Long projectId
			, @PathVariable Long boardId
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
		
		BoardResponse board = BoardResponse.convert(boardService.findTempBoard(boardId));
		
		BoardResource resource = new BoardResource(board);
		resource.add(WebMvcLinkBuilder.linkTo(BoardController.class).withRel("boards"));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/projects/{projectId}/temp-boards")
	public ResponseEntity<?> getTempBoards(
			@PathVariable Long projectId
			, @RequestParam("query") String query
			, Pageable pageable
			, PagedResourcesAssembler<BoardResponse> assembler
			) {
		
		Project project = projectService.findProject(projectId);
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
		
		Page<BoardResponse> boards = BoardResponse.convert(boardService.findTempBoards(query, pageable));

		return new ResponseEntity<>(assembler.toModel(boards, e -> new BoardResource(e)), HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/projects/{projectId}/temp-boards")
	public ResponseEntity<?> writeTempBoard(
			Principal principal
			, @PathVariable Long projectId
			, @RequestBody BoardWriteRequest params
			) {
		
		BoardResponse savedBoard = BoardResponse.convert(boardService.writeTempBoard(principal.getName(), projectId, params));
		BoardResource resource = new BoardResource(savedBoard);
		
		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
	@PutMapping("/api/v1/projects/{projectId}/temp-boards/{boardId}")
	public ResponseEntity<?> updateTempBoard(
			Principal principal
			, @PathVariable Long projectId
			, @PathVariable Long boardId
			, @RequestBody BoardWriteRequest params
			) {

		BoardResponse savedBoard = BoardResponse.convert(boardService.updateTempBoard(principal.getName(), projectId, boardId, params));
		BoardResource resource = new BoardResource(savedBoard);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/v1/projects/{projectId}/temp-boards/{boardId}")
	public ResponseEntity<?> deleteTempBoard(
			Principal principal
			, @PathVariable Long projectId
			, @PathVariable Long boardId
			) {

		boardService.deteleTempBoard(principal.getName(), projectId, boardId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/api/v1/projects/{projectId}/boards")
	public ResponseEntity<?> postingBoard(
			Principal principal
			, @PathVariable Long projectId
			, @RequestBody BoardWriteRequest params
			) {
		
		BoardResponse savedBoard = BoardResponse.convert(boardService.postBoard(principal.getName(), projectId, params));
		BoardResource resource = new BoardResource(savedBoard);
		
		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
	@PutMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> updateBoard(
			Principal principal
			, @PathVariable Long projectId
			, @PathVariable Long boardId
			, @RequestBody BoardWriteRequest params
			) {

		BoardResponse savedBoard = BoardResponse.convert(boardService.updateBoard(principal.getName(), projectId, boardId, params));
		BoardResource resource = new BoardResource(savedBoard);

		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/v1/projects/{projectId}/boards/{boardId}")
	public ResponseEntity<?> deleteBoard(
			Principal principal
			, @PathVariable Long projectId
			, @PathVariable Long boardId
			) {

		boardService.deteleBoard(principal.getName(), projectId, boardId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
