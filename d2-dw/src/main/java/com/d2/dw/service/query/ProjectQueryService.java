package com.d2.dw.service.query;

import java.util.List;

import com.d2.dw.domain.User;
import com.d2.dw.dto.ProjectDTO;

public interface ProjectQueryService {

	/**
	 * 본인 프로젝트 조회
	 * @param owner - 본인정보 전달
	 * @return 본인 프로젝트 리스트
	 */
	List<ProjectDTO> getMyProjects(User owner);

}
