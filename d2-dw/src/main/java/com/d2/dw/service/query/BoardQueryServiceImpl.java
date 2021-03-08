package com.d2.dw.service.query;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO;
import com.d2.dw.dto.BoardTreeDTO;
import com.d2.dw.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service("BoardQueryService")
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService{

	private final BoardRepository boardRepository;
	
	@Override
	public BoardDTO getBoard(Long boardId) {
		return BoardDTO.convert(boardRepository.findBoardById(boardId));
	}

	@Override
	public List<BoardTreeDTO> getBoardsTree(Project project, Map<String, String> params) {
		return BoardDTO.convert(boardRepository.findBoardTreeByProject(project));
	}

}
