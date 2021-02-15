package com.d1.ws.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.ws.domain.Project;
import com.d1.ws.repository.ProjectRepository;

@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Transactional(readOnly = true)
	@Override
	public Project getProject(Long projectId) {
		return projectRepository.findById(projectId).get();
	}

}
