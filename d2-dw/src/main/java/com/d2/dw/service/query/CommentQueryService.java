package com.d2.dw.service.query;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.dto.CommentDTO.CommentResponseDTO;

public interface CommentQueryService {

	Page<CommentResponseDTO> findCommentsOfBoard(Long projectId, Long boardId, Pageable pageable);

}
