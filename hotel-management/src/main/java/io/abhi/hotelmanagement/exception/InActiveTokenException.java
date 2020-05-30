package io.abhi.hotelmanagement.exception;

public class InActiveTokenException extends RuntimeException {
	
	public InActiveTokenException(String message) {
		super(message);
	}
	
	public InActiveTokenException(String message, Throwable cause) {
		super(message, cause);
	}

}
