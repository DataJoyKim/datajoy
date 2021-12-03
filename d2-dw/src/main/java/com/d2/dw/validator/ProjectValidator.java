package com.d2.dw.validator;

import org.springframework.stereotype.Component;

import com.d2.dw.domain.Project;
import com.d2.dw.domain.User;
import com.d2.dw.dto.ProjectDTO.ProjectWriteRequestDTO;

@Component("ProjectValidator")
public class ProjectValidator {

	public void validateCreateProject(User creator, ProjectWriteRequestDTO params) {
		// TODO Auto-generated method stub
		
	}

	public void validateUpdateProject(Project project, User creator, ProjectWriteRequestDTO params) {
		// TODO Auto-generated method stub
		
	}

	public void validateDeleteProject(Project project, User creator) {
		// TODO Auto-generated method stub
		
	}

}
