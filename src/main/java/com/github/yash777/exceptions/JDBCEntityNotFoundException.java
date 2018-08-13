package com.github.yash777.exceptions;

public class JDBCEntityNotFoundException extends Exception {

	private static final long serialVersionUID = 992971926160511772L;

	public JDBCEntityNotFoundException() {
		super();
	}
	public JDBCEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public JDBCEntityNotFoundException(Throwable cause) {
		super(cause);
	}
	
	private int errorCode;
	private String errorMessage;
	public JDBCEntityNotFoundException(String message) {
		super(message);
		this.errorMessage = message;
	}
	public JDBCEntityNotFoundException(String message, int errorCode) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = message;
	}


	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
}
