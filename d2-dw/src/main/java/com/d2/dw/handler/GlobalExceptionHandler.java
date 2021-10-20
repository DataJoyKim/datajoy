package com.d2.dw.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.d2.dw.exception.BusinessException;
import com.d2.dw.exception.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
			 
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
    	logger.error("handleBusinessException", e);
    	ErrorResponse response = new ErrorResponse(e.getCode(), e.getErrorKind(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatus()));
    }
}
