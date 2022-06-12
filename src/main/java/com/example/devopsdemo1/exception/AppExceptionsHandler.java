package com.example.devopsdemo1.exception;

/**
*
* @author TANUSREE
*/

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.devopsdemo1.model.ErrorMessage;



@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	
	/**
	* Method to Handle Internal Server Exception (500)
	* @param ex Accept Exception
	* @param request Accept Web Request
	* @return Customized generic object
	*/
	
	@ExceptionHandler(value = {Exception.class}) 
	public ResponseEntity<?> handleInternalServerException(Exception ex, WebRequest request){
		String errorDescription = ex.getLocalizedMessage();
		if(errorDescription == null) errorDescription = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), errorDescription, ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );

	}
	
	/**
	* Method to Handle Null Pointer Exception (404)
	* @param ex  Accept Exception
	* @param request Accept Web Request
	* @return Customized generic object
	*/
	
	@ExceptionHandler(value = {NullPointerException.class}) 
	public ResponseEntity<?> handleNullPointerException(NullPointerException ex, WebRequest request){
		String errorDescription = ex.getLocalizedMessage();
		if(errorDescription == null) errorDescription = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND.value(), errorDescription, ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND );
		
	}
	
}
