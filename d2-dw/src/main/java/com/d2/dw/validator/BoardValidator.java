package com.d2.dw.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.d2.dw.domain.Project;
import com.d2.dw.domain.User;
import com.d2.dw.dto.BoardDTO.BoardWriteRequest;
import com.d2.dw.error.BoardErrorCode;
import com.d2.dw.exception.BusinessException;

@Component
public class BoardValidator {
	
	public void validate(Project project, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	public void validateWriteTempBoard(User writer, Project project, BoardWriteRequest params) {
		if(writer == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_WRITER);
		}
		
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
	}

	public void validatePostBoard(User writer, Project project, BoardWriteRequest params) {
		if(writer == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_WRITER);
		}
		
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
		
		if(params.getTitle() == null || params.getTitle().isEmpty()) {
			throw new BusinessException(BoardErrorCode.FAULT_REQUEST_BY_TITLE_NULL);
		}
		
		if(params.getContent() == null || params.getContent().isEmpty()) {
			throw new BusinessException(BoardErrorCode.FAULT_REQUEST_BY_CONTENTS_NULL);
		}
	}

	public void validateUpdateTempBoard(User writer, Project project, BoardWriteRequest params) {
		if(writer == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_WRITER);
		}
		
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
	}

	public void validateDeleteTempBoard(User writer, Project project) {
		if(writer == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_WRITER);
		}
		
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
	}

	public void validateUpdateBoard(User writer, Project project, BoardWriteRequest params) {
		if(writer == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_WRITER);
		}
		
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
	}

	public void validateDeleteBoard(User writer, Project project) {
		if(writer == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_WRITER);
		}
		
		if(project == null) {
			throw new BusinessException(BoardErrorCode.NOT_FOUND_PROJECT);
		}
	}
}
