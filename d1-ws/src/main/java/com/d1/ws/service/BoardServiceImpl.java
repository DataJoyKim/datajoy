package com.d1.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.ws.domain.Board;
import com.d1.ws.repository.BoardRepository;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Board findById(Long id) {
		Board boards = boardRepository.findById(id).get();
		
		return boards;
	}

}
