package com.d2.dw.service.query;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.d2.dw.domain.Board;
import com.d2.dw.dto.CommentDTO;
import com.d2.dw.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service("CommentQueryService")
@RequiredArgsConstructor
public class CommentQueryServiceImpl implements CommentQueryService {
	
	private final CommentRepository commentRepository;
	
	@Override
	public Page<CommentDTO> getCommentsByBoard(Board board, Pageable pageable) {
		
		PageRequest r = PageRequest.of(0, 10);
		
		return CommentDTO.convert(commentRepository.findByBoard(board, r));
	}

}
