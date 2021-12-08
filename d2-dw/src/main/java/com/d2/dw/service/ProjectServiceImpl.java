package com.d2.dw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.domain.Project;
import com.d2.dw.domain.User;
import com.d2.dw.dto.ProjectDTO.ProjectResponseDTO;
import com.d2.dw.dto.ProjectDTO.ProjectWriteRequestDTO;
import com.d2.dw.repository.ProjectRepository;
import com.d2.dw.repository.UserRepository;
import com.d2.dw.validator.ProjectValidator;

import lombok.RequiredArgsConstructor;

@Service("ProjectService")
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	
	private final ProjectRepository projectRepository; 
	private final UserRepository userRepository;
	private final ProjectValidator projectValidator;
	
	@Transactional(readOnly = true)
	@Override
	public Project findProject(Long projectId) {
		return projectRepository.findById(projectId).get();
	}

	@Transactional
	@Override
	public ProjectResponseDTO createProject(String name, ProjectWriteRequestDTO params) {
		User creator = userRepository.findByEmail(name).get();
		
		Project project = Project.createProject(projectValidator, creator, params);
		
		return ProjectResponseDTO.of(projectRepository.save(project));
	}

	@Transactional
	@Override
	public ProjectResponseDTO updateProjectBy(Long projectId, String name, ProjectWriteRequestDTO params) {
		User creator = userRepository.findByEmail(name).get();
		Project project = projectRepository.findById(projectId).get();
		
		project.updateProject(projectValidator, creator, params);
		
		return ProjectResponseDTO.of(projectRepository.save(project));
	}

	@Transactional
	@Override
	public void deleteProjectBy(Long projectId, String name) {
		User creator = userRepository.findByEmail(name).get();
		Project project = projectRepository.findById(projectId).get();
		
		project.deleteProject(projectValidator, creator);
		
		projectRepository.delete(project);
	}
}
