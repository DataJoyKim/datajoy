package com.d2.dw.service;

import com.d2.dw.dto.CommentDTO.CommentResponseDTO;
import com.d2.dw.dto.CommentDTO.CommentWriteRequestDTO;
import com.d2.dw.exception.BusinessException;

public interface CommentService {
	CommentResponseDTO writeComment(String username, Long projectId, Long boardId, CommentWriteRequestDTO params) throws BusinessException;
	
	CommentResponseDTO updateComment(String username, Long projectId, Long boardId, Long commentId, CommentWriteRequestDTO params) throws BusinessException;
	
	void deleteComment(String username, Long projectId, Long boardId, Long commentId) throws BusinessException;
}
