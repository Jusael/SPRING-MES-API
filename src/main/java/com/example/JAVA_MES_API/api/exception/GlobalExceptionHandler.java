package com.example.JAVA_MES_API.api.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.JAVA_MES_API.api.dao.UserDaoImpl;
import com.example.JAVA_MES_API.api.dto.ErrorResponse; // ErrorResponse DTO 경로 맞게 변경
import com.example.JAVA_MES_API.api.exception.BusinessException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
    // INTERNAL_SERVER_ERROR 용
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
    	
    	log.error("INTERNAL_SERVER_ERROR", e);
    	
        ErrorResponse response = new ErrorResponse(false, "서버 오류 발생", "500");
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // BLL 전용 Exception
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
    	
    	log.error("Business_Exception", e);
    	
        ErrorResponse response = new ErrorResponse(false, e.getMessage(), e.getErrorCode());
        
        return ResponseEntity.badRequest().body(response);
    }

    // BAD REQUEST
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
    	
    	log.error("BAD_REQUEST", e);
    	
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        
        ErrorResponse response = new ErrorResponse(false, message, "BAD_REQUEST");
        
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(SQLException e) {
    	
    	log.error("SQL Exception error", e);
    	
        String message = "데이터 처리 과정중 오류 발생";
        
        ErrorResponse response = new ErrorResponse(false, message, "SQL_ERROR");
        
        return ResponseEntity.badRequest().body(response);
    }
}
