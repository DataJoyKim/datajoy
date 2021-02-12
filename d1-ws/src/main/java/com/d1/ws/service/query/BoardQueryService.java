package com.d1.ws.service.query;

import java.util.List;
import java.util.Map;

import com.d1.ws.domain.Project;
import com.d1.ws.service.dto.BoardDTO;
import com.d1.ws.service.dto.BoardTreeDTO;

/**
 * API 조회용 서비스. DTO 변환하여 전달.
 * @author 김낙영
 *
 */
public interface BoardQueryService {
	/**
	 * id로 특정 게시글 가져오기
	 * @param boardId - 게시글 번호
	 * @return 특정 게시글
	 */
	BoardDTO findBoard(Long boardId);
	
	/**
	 * 프로젝트에 해당하는 게시글 트리를 가져오기
	 * @param projectId - 프로젝트 번호
	 * @param params - 파라미터
	 * @return 게시글 리스트
	 */
	List<BoardTreeDTO> findBoardsTree(Project project, Map<String, String> params);
}
