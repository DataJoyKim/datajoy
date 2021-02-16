package com.d1.ws.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.d1.ws.domain.Project;

@Component
public class BoardValidator {
	
	public void validate(Project project, Errors errors) {
		if(project == null) {
			//errors.rejectValue();
		}
	}
}
