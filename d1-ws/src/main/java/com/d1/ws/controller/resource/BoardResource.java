package com.d1.ws.controller.resource;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.d1.ws.controller.BoardController;
import com.d1.ws.service.dto.BoardDTO;
import com.d1.ws.service.dto.BoardTreeDTO;

public class BoardResource extends ResourceSupport {
	private Object content;
	
	public BoardResource(BoardDTO board) {
		this.content = board;
		this.add(ControllerLinkBuilder.linkTo(BoardController.class).slash(board.getId()).withSelfRel());
	}
	
	public BoardResource(List<BoardTreeDTO> boards) {
		this.content = boards;
		this.add(ControllerLinkBuilder.linkTo(BoardController.class).withSelfRel());
	}
	
	public Object getContent() {
		return this.content;
	}
}
