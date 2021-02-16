package com.d1.ws.service.query;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.ws.domain.Project;
import com.d1.ws.repository.BoardRepository;
import com.d1.ws.service.dto.BoardDTO;
import com.d1.ws.service.dto.BoardTreeDTO;

@Transactional(readOnly = true)
@Service("BoardQueryService")
public class BoardQueryServiceImpl implements BoardQueryService{
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<BoardTreeDTO> findBoardsTree(Project project, Map<String, String> params) {
		return BoardDTO.convert(boardRepository.findBoardTreeByProject(project));
	}
	
	@Override
	public BoardDTO findBoard(Long id) {
		return BoardDTO.convert(boardRepository.findByIdQuerydsl(id));
	}

}
