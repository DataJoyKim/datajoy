package com.d2.dw.service;

import org.springframework.stereotype.Service;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Comment;
import com.d2.dw.domain.Project;
import com.d2.dw.domain.User;
import com.d2.dw.dto.CommentDTO.CommentResponseDTO;
import com.d2.dw.dto.CommentDTO.CommentWriteRequestDTO;
import com.d2.dw.exception.BusinessException;
import com.d2.dw.repository.BoardRepository;
import com.d2.dw.repository.CommentRepository;
import com.d2.dw.repository.ProjectRepository;
import com.d2.dw.repository.UserRepository;
import com.d2.dw.validator.CommentValidator;

import lombok.RequiredArgsConstructor;

@Service("CommentService")
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;
	private final CommentValidator commentValidator;
	
	@Override
	public CommentResponseDTO writeComment(String username, Long projectId, Long boardId, CommentWriteRequestDTO params)
			throws BusinessException {
		
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		Board board = boardRepository.findById(boardId).get();
		
		Comment comment = Comment.writeComment(commentValidator, writer, project, board, params);
		 
		return CommentResponseDTO.of(commentRepository.save(comment));
	}

	@Override
	public CommentResponseDTO updateComment(String username, Long projectId, Long boardId, Long commentId,
			CommentWriteRequestDTO params) throws BusinessException {
		
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		Board board = boardRepository.findById(boardId).get();
		Comment comment = commentRepository.findById(commentId).get();
		
		comment.updateComment(commentValidator, writer, project, board, params);
		
		return CommentResponseDTO.of(commentRepository.save(comment));
	}

	@Override
	public void deleteComment(String username, Long projectId, Long boardId, Long commentId)
			throws BusinessException {
		
		User writer = userRepository.findByEmail(username).get();
		Project project = projectRepository.findById(projectId).get();
		Board board = boardRepository.findById(boardId).get();
		Comment comment = commentRepository.findById(commentId).get();
		
		comment.deleteComment(commentValidator, writer, project, board);
		
		commentRepository.delete(comment);
	}

}
