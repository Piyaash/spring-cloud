package com.example.demorestfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demorestfulwebservices.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class MyExceptionHandler extends ResponseEntityExceptionHandler{
	

	@ExceptionHandler
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		
		MyException exception=new MyException(new Date(),ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity(exception,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	

	@ExceptionHandler
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request) throws Exception {
		
		MyException exception=new MyException(new Date(),ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity(exception,HttpStatus.NOT_FOUND);	
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		MyException exception=new MyException(new Date(),"Validation Failed",ex.getBindingResult().toString());
		 return new ResponseEntity(exception,HttpStatus.BAD_REQUEST);
		
	}

}
