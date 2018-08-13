package com.github.yash777.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ExceptionHandlerExceptionResolver « Resolved exception caused by Handler execution
 * 
 * https://stackoverflow.com/a/47918918/5081877
 * @author yashwanth.m
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestCustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(JDBCEntityNotFoundException.class)
	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object processValidationError(JDBCEntityNotFoundException ex, WebRequest request) {
		RestException_ResponseDetails response =
			new RestException_ResponseDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return response;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RestException_ResponseDetails> handleRuntimeException(RuntimeException ex, WebRequest request) {
		RestException_ResponseDetails errorDetails = 
			new RestException_ResponseDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception e)   {
		return new ModelAndView("error");
	}
	
	/*@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
		Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
		return new ModelAndView("404");
	}*/
}