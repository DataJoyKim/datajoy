package com.d1.ws.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.ws.domain.Project;
import com.d1.ws.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service("ProjectService")
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;

	@Transactional(readOnly = true)
	@Override
	public Project getProject(Long projectId) {
		return projectRepository.findById(projectId).get();
	}

}
