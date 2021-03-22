package com.d2.dw.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.d2.dw.domain.EntityCreateUpdateData;
import com.d2.dw.domain.Project;

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
		//projectDto.setUser(UserDTO.convert(project.get));
		
		return projectDto;
	}

	public static List<ProjectDTO> convert(List<Project> projects) {
		return projects.stream()
						.map(o -> ProjectDTO.convert(o))
						.collect(Collectors.toList());
	}
}
