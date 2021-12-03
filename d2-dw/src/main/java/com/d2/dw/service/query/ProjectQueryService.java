package com.d2.dw.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.d2.dw.dto.ProjectDTO.ProjectResponseDTO;

public interface ProjectQueryService {

	Page<ProjectResponseDTO> findProject(Pageable pageable);

	ProjectResponseDTO findProjectBy(Long projectId);

}
