package com.d2.dw.service.query;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2.dw.domain.User;
import com.d2.dw.dto.ProjectDTO;
import com.d2.dw.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("ProjectQueryService")
@RequiredArgsConstructor
public class ProjectQueryServiceImpl implements ProjectQueryService {
	
	private final ProjectRepository projectRepository; 
	
	@Override
	public List<ProjectDTO> getMyProjects(User owner) {
		return ProjectDTO.convert(projectRepository.findAll());
	}

}
