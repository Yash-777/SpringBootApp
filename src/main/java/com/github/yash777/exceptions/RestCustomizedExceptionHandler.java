package com.github.yash777.exceptions;

import java.util.Date;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ExceptionHandlerExceptionResolver « Resolved exception caused by Handler execution
 * 
 * https://stackoverflow.com/a/47918918/5081877
 * 
 * @author yashwanth.m
 *
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class RestCustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * http://localhost:8080/student/jdbc/findById/777
	 */
	@ExceptionHandler(JDBCEntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Data not available for the requested id.")
	@ResponseBody
	public Object processValidationError(JDBCEntityNotFoundException ex, WebRequest request) {
		System.out.println("« « « « « JDBCEntityNotFoundException : "+ ex.getMessage());
		// EmptyResultDataAccessException « Incorrect result size: expected 1, actual 0
		RestException_ResponseDetails response =
			new RestException_ResponseDetails(new Date(), ex.getMessage(), request.getDescription(false));
		//return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "« « « « « ResourceNotFoundException")
	@ResponseBody
	public Object processValidationError2(ResourceNotFoundException ex, WebRequest request) {
		System.out.println("« « « « « ResourceNotFoundException");
		RestException_ResponseDetails response =
			new RestException_ResponseDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return response;
	}
	
	/**
	 * 
	 * http://localhost:8080/r/exception («) throw new RuntimeException("test exception");
	 * {"timestamp":"2018-08-08T13:06:06.153+0000","message":"test exception","details":"uri=/exception"}
	 * 
	 * http://localhost:8080/student/orm/findById/44
	 * {"timestamp":1542118415571,"message":"No value present","details":"uri=/student/orm/findById/44"}
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RestException_ResponseDetails> handleRuntimeException(RuntimeException ex, WebRequest request) {
		System.out.println("« « « « « RuntimeException");
		
		RestException_ResponseDetails errorDetails = 
			new RestException_ResponseDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	/*
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception e) {
		System.out.println("« « « « « handleError");
		return new ModelAndView("myErrorPage");
	}
	
	@ExceptionHandler(NoSuchMethodException.class)
	public ModelAndView handleError405(HttpServletRequest request, Exception e) {
		System.out.println("« « « « « NoSuchMethodException"); // java.util.logging
		//Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
		return new ModelAndView("405");
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleError404(NoHandlerFoundException request, Exception e) {
		System.out.println("« « « « « RuntimeException"); // java.util.logging
		Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
		return new ModelAndView("404");
	}
	*/
}