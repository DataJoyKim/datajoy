package com.d1.ws.domain.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.d1.ws.controller.BoardController;
import com.d1.ws.domain.Board;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class BoardResource extends ResourceSupport {
	
	@JsonUnwrapped
	private Board board;
	
	public BoardResource(Board board) {
		this.board = board;
		this.add(ControllerLinkBuilder.linkTo(BoardController.class).slash(board.getId()).withSelfRel());
	}
	
	public Board getBoard() {
		return this.board;
	}
}
