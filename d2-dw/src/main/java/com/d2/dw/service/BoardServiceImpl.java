package com.d2.dw.service;

import org.springframework.stereotype.Service;

import com.d2.dw.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service("BoardService")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
 
	private final BoardRepository boardRepository; 
}
