package com.arquitecturajava;

public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataBaseException() {
		super();
	}
	
	public DataBaseException(String message) {
		super(message);
	}
	
	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataBaseException(Throwable cause) {
		super(cause);
	}
	
}
