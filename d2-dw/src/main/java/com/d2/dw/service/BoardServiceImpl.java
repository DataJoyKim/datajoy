package com.d2.dw.service;

import org.springframework.stereotype.Service;

import com.d2.dw.domain.Board;
import com.d2.dw.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service("BoardService")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
 
	private final BoardRepository boardRepository;

	@Override
	public Board getBoardById(Long boardId) {
		return boardRepository.findById(boardId).get();
	} 
	
	
}
