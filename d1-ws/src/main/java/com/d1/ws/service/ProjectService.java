package com.d1.ws.service;

import com.d1.ws.domain.Project;

public interface ProjectService {

	/**
	 * 특정 프로젝트 가져오기
	 * @param projectId - 프로젝트 id
	 * @return 특정 프로젝트
	 */
	Project getProject(Long projectId);

}
