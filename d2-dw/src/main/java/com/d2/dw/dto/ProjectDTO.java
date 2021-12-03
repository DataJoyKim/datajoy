package com.d2.dw.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.d2.dw.domain.EntityCreateUpdateData;
import com.d2.dw.domain.Project;

import lombok.Getter;
import lombok.Setter;

public class ProjectDTO {

	@Getter @Setter
	public static class ProjectResponseDTO { 
		private Long id;
		
		private String projectNm;
		
		private String description;
		
		private EntityCreateUpdateData entityCreateUpdateData;
		
		private UserDTO user;
		
		public static ProjectResponseDTO of(Project project) {
			if(project == null) return null; 
			
			ModelMapper mapper = new ModelMapper();
			mapper.createTypeMap(Project.class, ProjectResponseDTO.class)
					.addMappings(mapping -> mapping.skip(ProjectResponseDTO::setUser));
			
			ProjectResponseDTO projectDto = mapper.map(project, ProjectResponseDTO.class);
			//projectDto.setUser(UserDTO.convert(project.get));
			
			return projectDto;
		}
	
		public static List<ProjectResponseDTO> of(List<Project> projects) {
			return projects.stream()
							.map(o -> ProjectResponseDTO.of(o))
							.collect(Collectors.toList());
		}

		public static Page<ProjectResponseDTO> of(Page<Project> projects) {
			return projects.map(o -> ProjectResponseDTO.of(o));
		}
	}

	@Getter
	public static class ProjectWriteRequestDTO {
		private String projectNm;
		
		private String description;
	}
}
