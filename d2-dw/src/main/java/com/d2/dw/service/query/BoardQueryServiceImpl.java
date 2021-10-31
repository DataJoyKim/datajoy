package com.d2.dw.service.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO;
import com.d2.dw.dto.BoardDTO.BoardResponse;
import com.d2.dw.dto.BoardDTO.SaveBoardRequest;
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
	public List<BoardTreeDTO.BoardTreeResponse> getBoardsTree(Project project) {
		return BoardTreeDTO.BoardTreeResponse.convert(boardRepository.findBoardTreeByProject(project));
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepository.delete(board);
	}

	@Override
	public Page<BoardResponse> findBoards(Project project, String query, Pageable pageable) {
		return BoardResponse.convert(boardRepository.findAll(pageable));
	}

	@Override
	public BoardResponse insertBoard(Project project, SaveBoardRequest params) {
		Board board = Board.createBoard(project, params); 
		
		return BoardResponse.convert(boardRepository.save(board));
	}
}
