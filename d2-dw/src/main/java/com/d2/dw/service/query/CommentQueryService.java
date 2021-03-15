package com.d2.dw.service.query;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.domain.Board;
import com.d2.dw.dto.CommentDTO;

public interface CommentQueryService {

	Page<CommentDTO> getCommentsByBoard(Board board, Pageable pageable);

}
