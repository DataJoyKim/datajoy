package com.d1.ws.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.ws.domain.Board;
import com.d1.ws.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service("BoardService")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Board findById(Long id) {
		Board boards = boardRepository.findById(id).get();
		
		return boards;
	}

}
