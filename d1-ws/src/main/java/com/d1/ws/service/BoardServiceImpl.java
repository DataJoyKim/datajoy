package com.d1.ws.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.ws.domain.Board;
import com.d1.ws.domain.Project;
import com.d1.ws.dto.BoardDTO;
import com.d1.ws.repository.BoardRepository;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Board getBoard(Long id) {
		Board boards = boardRepository.findById(id);
		
		return boards;
	}

	@Transactional(readOnly = true)
	@Override
	public List<BoardDTO> getBoardsTree(Project project, Map<String, String> params) {
		List<Board> boards = boardRepository.findByProjectAndParent(project, null);
		
		List<BoardDTO> result = boards.stream()
										.map(a -> new BoardDTO(a))
										.collect(Collectors.toList());
		
		return result;
	}

}
