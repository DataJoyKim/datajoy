package com.d1.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d1.ws.domain.Board;
import com.d1.ws.repository.BoardRepository;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public Board findByBoardId(Long id) {
		Board boards = boardRepository.findById(id);
		
		return boards;
	}

	@Override
	public List<Board> findAll() {
		return boardRepository.findAll();
	}

}
