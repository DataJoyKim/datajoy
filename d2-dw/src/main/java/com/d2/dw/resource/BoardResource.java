package com.d2.dw.resource;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.d2.dw.controller.BoardController;
import com.d2.dw.dto.BoardDTO;
import com.d2.dw.dto.BoardTreeDTO;

public class BoardResource extends RepresentationModel<BoardResource> {
	private Object content;
	
	public BoardResource(BoardDTO board) {
		this.content = board;
		this.add(WebMvcLinkBuilder.linkTo(BoardController.class).withSelfRel());
	}
	
	public BoardResource(List<BoardTreeDTO> boards) {
		this.content = boards;
		this.add(WebMvcLinkBuilder.linkTo(BoardController.class).withSelfRel());
	}
	
	public Object getContent() {
		return this.content;
	}

}
