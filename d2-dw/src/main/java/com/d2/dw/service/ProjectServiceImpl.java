package com.d2.dw.service;

import org.springframework.stereotype.Service;

import com.d2.dw.domain.Project;
import com.d2.dw.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service("ProjectService")
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	private final ProjectRepository projectRepository; 
	
	@Override
	public Project findProject(Long projectId) {
		return projectRepository.findById(projectId).get();
	}
}
