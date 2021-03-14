package com.d2.dw.service.query;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import com.d2.dw.domain.Board;
import com.d2.dw.dto.CommentDTO;

public interface CommentQueryService {

	Page<CommentDTO> getCommentsByBoard(Board board, Pageable pageable);

}
