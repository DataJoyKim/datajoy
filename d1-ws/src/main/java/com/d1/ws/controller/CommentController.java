package com.d1.ws.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.ws.controller.resource.CommentResource;
import com.d1.ws.domain.Board;
import com.d1.ws.service.BoardService;
import com.d1.ws.service.dto.CommentDTO;
import com.d1.ws.service.query.CommentQueryService;
import com.d1.ws.util.PageableUtil;

@RestController
@RequestMapping(produces = "application/hal+json")
public class CommentController {
	@Autowired
	private CommentQueryService commentQueryService;
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/api/v1/projects/{projectId}/boards/{boardId}/comments")
	public ResponseEntity<?> findAllComments(@PathVariable Long boardId, @RequestParam Map<String,String> params, PagedResourcesAssembler<CommentDTO> assembler){
		Board board = boardService.findById(boardId);
		if(board == null) {
			//throw exception
		}
		
		Page<CommentDTO> comments = commentQueryService.findAll(board, PageableUtil.pageable(params));
		return new ResponseEntity<>(assembler.toResource(comments, e -> new CommentResource(e)), HttpStatus.OK);
	}
}
