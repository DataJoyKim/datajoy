package com.d2.dw.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.code.BoardStatus;
import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.domain.User;
import com.d2.dw.dto.BoardDTO.BoardWriteRequest;
import com.d2.dw.repository.BoardRepository;
import com.d2.dw.repository.ProjectRepository;
import com.d2.dw.repository.UserRepository;
import com.d2.dw.validator.BoardValidator;

import lombok.RequiredArgsConstructor;

@Service("BoardService")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
 
	private final BoardRepository boardRepository;
	private final BoardValidator boardValidator;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Board findBoard(Long boardId) {
		return boardRepository.findBoardByIdAndStatus(boardId, BoardStatus.POSTING);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Board> findBoards(String query, Pageable pageable) {
		Page<Board> boardPages = boardRepository.findByStatus(BoardStatus.POSTING, pageable);
		
		return boardPages;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Board> findTempBoards(String query, Pageable pageable) {

		Page<Board> boardPages = boardRepository.findByStatus(BoardStatus.SAVE, pageable);
		
		return boardPages;
	}


	@Transactional
	@Override
	public Board writeTempBoard(String username, Long projectId, BoardWriteRequest params) { 
		// 엔티티 조회
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		
		// 임시 게시글 작성
		Board board = Board.writeTempBoard(boardValidator, writer, project, params); 
		
		return boardRepository.save(board);
	}
	
	@Transactional
	@Override
	public Board updateTempBoard(String username, Long projectId, Long boardId, BoardWriteRequest params) {
		// 엔티티 조회
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		Board board = boardRepository.findById(boardId).get();
		
		// 임시 게시글 수정
		board.updateTempBoard(boardValidator, writer, project, params);
		
		return boardRepository.save(board);
	}

	@Transactional
	@Override
	public void deteleTempBoard(String username, Long projectId, Long boardId) {
		// 엔티티 조회
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		Board board = boardRepository.findById(boardId).get();
		
		// 임시 게시글 삭제
		board.deleteTempBoard(boardValidator, writer, project);
		
		// 임시 게시글 엔티티 삭제
		boardRepository.delete(board);
	}

	@Transactional
	@Override
	public Board postBoard(String username, Long projectId, BoardWriteRequest params) {
		// 엔티티 조회
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		
		// 게시글 포스팅
		Board board = Board.postingBoard(boardValidator, writer, project, params); 
		
		return boardRepository.save(board);
	}

	@Transactional
	@Override
	public Board updateBoard(String username, Long projectId, Long boardId, BoardWriteRequest params) {
		// 엔티티 조회
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		Board board = boardRepository.findById(boardId).get();
		
		// 임시 게시글 수정
		board.updateBoard(boardValidator, writer, project, params); 
		
		return boardRepository.save(board);
	}

	@Transactional
	@Override
	public void deteleBoard(String username, Long projectId, Long boardId) {
		// 엔티티 조회
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		Board board = boardRepository.findById(boardId).get();
		
		// 게시글 삭제
		board.deleteBoard(boardValidator, writer, project);
		
		// 게시글 엔티티 삭제
		boardRepository.delete(board);
	}

	@Override
	public Board findTempBoard(Long boardId) {
		return boardRepository.findBoardByIdAndStatus(boardId, BoardStatus.SAVE);
	}
	
	
}
