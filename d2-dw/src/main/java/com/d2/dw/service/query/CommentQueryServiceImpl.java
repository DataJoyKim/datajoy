package com.d2.dw.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Project;
import com.d2.dw.dto.CommentDTO.CommentResponseDTO;
import com.d2.dw.error.CommentErrorCode;
import com.d2.dw.exception.BusinessException;
import com.d2.dw.repository.BoardRepository;
import com.d2.dw.repository.CommentRepository;
import com.d2.dw.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("CommentQueryService")
@RequiredArgsConstructor
public class CommentQueryServiceImpl implements CommentQueryService {
	
	private final ProjectRepository projectRepository;
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;
	
	@Override
	public Page<CommentResponseDTO> findCommentsOfBoard(Long projectId, Long boardId, Pageable pageable) {
		Project project = projectRepository.findById(projectId).get();
		if(project == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_PROJECT_NULL);
		}
		
		Board board = boardRepository.findById(boardId).get();
		if(board == null) {
			throw new BusinessException(CommentErrorCode.FAULT_REQUEST_BY_BOARD_NULL);
		}
		
		return CommentResponseDTO.of(commentRepository.findCommentsOfBoard(project, board, pageable));
	}

}
