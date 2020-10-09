package com.claro.rest.webservices.test.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Spring Framework possui a classe ResponseEntityExceptionHandler para lidar com exceções
// em requests de forma centralizada.

//Podemos aplicar ResponseEntityExceptionHandler para todos os controllers da API RESTFul.
//A anotação @ControllerAdvice permite que a classe ResponseEntityExceptionHandler se aplique a todos os
//controllers presentes no projeto.

//Setando aqui o handling de todas as exceções nas requests com resposta 500 - Interval Server Error

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse =
				new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		
		// Primeiro argumento é o objeto a ser serializado para JSON definido em nossa classe
		// ExceptionResponse.java e o segundo argumento é o HTTP_STATUS padrão a ser mostrado para 
		// todas as exceções genéricas que ocorrerem em requests.
		
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		
	}
	
	

}
