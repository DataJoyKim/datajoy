package com.d2.dw.service;

import com.d2.dw.domain.Project;
import com.d2.dw.dto.ProjectDTO.ProjectResponseDTO;
import com.d2.dw.dto.ProjectDTO.ProjectWriteRequestDTO;

public interface ProjectService {

	Project findProject(Long projectId);

	/**
	 * 본인 프로젝트 생성
	 * @param name
	 * @param params
	 * @return
	 */
	ProjectResponseDTO createProject(String name, ProjectWriteRequestDTO params);
	
	ProjectResponseDTO updateProjectBy(Long projectId, String name, ProjectWriteRequestDTO params);
	
	void deleteProjectBy(Long projectId, String name);
}
