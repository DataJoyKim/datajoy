package com.d2.dw.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.dto.BoardDTO.BoardResponseDTO;
import com.d2.dw.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service("BoardQueryService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService {

	private final BoardRepository boardRepository;
	
	@Override
	public BoardResponseDTO findBoard(Long boardId) {
		return BoardResponseDTO.of(boardRepository.findBoardByIdAndStatus(boardId, BoardStatus.POSTING));
	}

	@Override
	public Page<BoardResponseDTO> findBoards(String query, Pageable pageable) {
		return BoardResponseDTO.of(boardRepository.findByStatus(BoardStatus.POSTING, pageable));
	}

	@Override
	public Page<BoardResponseDTO> findTempBoards(String query, Pageable pageable) {
		return BoardResponseDTO.of(boardRepository.findByStatus(BoardStatus.SAVE, pageable));
	}
	@Override
	public BoardResponseDTO findTempBoard(Long boardId) {
		return BoardResponseDTO.of(boardRepository.findBoardByIdAndStatus(boardId, BoardStatus.SAVE));
	}
	

}
