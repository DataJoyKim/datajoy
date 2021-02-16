package com.d1.ws.service.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.ws.domain.Board;
import com.d1.ws.repository.CommentRepository;
import com.d1.ws.service.dto.CommentDTO;

@Transactional(readOnly = true)
@Service("CommentQueryService")
public class CommentQueryServiceImpl implements CommentQueryService {

	@Autowired
	CommentRepository commentRepository; 
	
	@Override
	public Page<CommentDTO> findAll(Board board, PageRequest pageable) {
		return CommentDTO.convert(commentRepository.findAll(pageable));
	}
	
}
