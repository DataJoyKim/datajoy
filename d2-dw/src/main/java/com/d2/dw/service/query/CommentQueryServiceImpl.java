package com.d2.dw.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.domain.Board;
import com.d2.dw.dto.CommentDTO;
import com.d2.dw.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("CommentQueryService")
@RequiredArgsConstructor
public class CommentQueryServiceImpl implements CommentQueryService {
	
	private final CommentRepository commentRepository;
	
	@Override
	public Page<CommentDTO> getCommentsByBoard(Board board, Pageable pageable) {
		return CommentDTO.convert(commentRepository.findByBoard(board, pageable));
	}

}
