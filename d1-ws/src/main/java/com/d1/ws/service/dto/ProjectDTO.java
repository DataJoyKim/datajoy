package com.d1.ws.service.dto;

import org.modelmapper.ModelMapper;

import com.d1.ws.domain.EntityCreateUpdateData;
import com.d1.ws.domain.Project;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectDTO {
	private Long id;
	private String projectNm;
	private String description;
	private EntityCreateUpdateData entityCreateUpdateData;
	private UserDTO user;
	
	public static ProjectDTO convert(Project project) {
		if(project == null) return null; 
		
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(Project.class, ProjectDTO.class)
				.addMappings(mapping -> mapping.skip(ProjectDTO::setUser));
		
		ProjectDTO projectDto = mapper.map(project, ProjectDTO.class);
		projectDto.setUser(UserDTO.convert(project.getUser()));
		
		return projectDto;
	}
}
