package com.d2.dw.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.d2.dw.domain.Project;
import com.d2.dw.dto.BoardDTO.BoardWriteRequest;
import com.d2.dw.error.BoardErrorCode;
import com.d2.dw.exception.BusinessException;

@Component
public class BoardValidator {
	
	public void validate(Project project, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	public void validateWrite(Project project, BoardWriteRequest params) {
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
	}

	public void validatePosting(Project project, BoardWriteRequest params) {
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
	}
}
