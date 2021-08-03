package com.d2.dw.service.query;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO;
import com.d2.dw.dto.BoardTreeDTO;
import com.d2.dw.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("BoardQueryService")
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService{

	private final BoardRepository boardRepository;
	
	@Override
	public BoardDTO.BoardResponse getBoard(Long boardId) {
		return BoardDTO.BoardResponse.convert(boardRepository.findBoardById(boardId));
	}

	@Override
	public List<BoardTreeDTO> getBoardsTree(Project project, Map<String, String> params) {
		return BoardDTO.BoardResponse.convert(boardRepository.findBoardTreeByProject(project));
	}

	@Override
	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepository.delete(board);;
	}

}
