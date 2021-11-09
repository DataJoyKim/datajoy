package com.d2.dw.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.domain.User;
import com.d2.dw.dto.BoardDTO;
import com.d2.dw.dto.BoardDTO.BoardResponse;
import com.d2.dw.dto.BoardDTO.BoardWriteRequest;
import com.d2.dw.repository.BoardRepository;
import com.d2.dw.repository.ProjectRepository;
import com.d2.dw.repository.UserRepository;
import com.d2.dw.validator.BoardValidator;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("BoardQueryService")
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService{

	private final BoardRepository boardRepository;
	private final BoardValidator boardValidator;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	
	@Override
	public BoardDTO.BoardResponse getBoard(Long boardId) {
		return BoardDTO.BoardResponse.convert(boardRepository.findBoardById(boardId));
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
	public BoardResponse writeTempBoard(Long userId, Long projectId, BoardWriteRequest params) { 
		// 엔티티 조회
		User writer = userRepository.findById(userId).get();
		Project project = projectRepository.findById(projectId).get();
		
		// 임시 게시글 작성
		Board board = Board.writeTempBoard(boardValidator, writer, project, params); 
		
		return BoardResponse.convert(boardRepository.save(board));
	}
}
