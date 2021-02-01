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
import com.d1.ws.domain.resource.BoardResource;
import com.d1.ws.service.BoardService;

@RestController
@RequestMapping(value = "/api/boards", produces = "application/hal+json")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public ResponseEntity<List<Board>> getBoardAll(@RequestParam Map<String, String> params){
		List<Board> boards = boardService.findAll();

		return ResponseEntity.ok(boards);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BoardResource> getBoard(@PathVariable long id, @RequestParam Map<String, String> params){
		Board board = boardService.findByBoardId(id);
		 
		BoardResource boardResource = new BoardResource(board);
		boardResource.add(ControllerLinkBuilder.linkTo(BoardController.class).withRel("query-boards"));
		return ResponseEntity.ok(boardResource);
	}
}
