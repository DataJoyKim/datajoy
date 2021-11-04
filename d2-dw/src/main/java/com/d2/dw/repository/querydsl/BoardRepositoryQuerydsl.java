package com.d2.dw.repository.querydsl;

import com.d2.dw.domain.Board;

public interface BoardRepositoryQuerydsl {
	Board findBoardById(Long id);
}
