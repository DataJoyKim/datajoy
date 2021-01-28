package com.d1.ws.service;

import java.util.List;

import com.d1.ws.domain.Board;

public interface BoardService {

	List<Board> findByBoardId(long id);

}
