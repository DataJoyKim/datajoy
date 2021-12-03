package com.d2.dw.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.dto.ProjectDTO.ProjectResponseDTO;
import com.d2.dw.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("ProjectQueryService")
@RequiredArgsConstructor
public class ProjectQueryServiceImpl implements ProjectQueryService {
	 
	private final ProjectRepository projectRepository; 

	@Override
	public Page<ProjectResponseDTO> findProject(Pageable pageable) {
		return ProjectResponseDTO.of(projectRepository.findAll(pageable));
	}

	@Override
	public ProjectResponseDTO findProjectBy(Long projectId) {
		return ProjectResponseDTO.of(projectRepository.findById(projectId).get());
	}

}
