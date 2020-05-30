package io.abhi.hotelinfoservice.exception;

public class InValidHotelIdException extends RuntimeException{
	
	public InValidHotelIdException(String message) {
		super(message);
	}
	
	public InValidHotelIdException(String message, Throwable cause) {
		super(message, cause);
	}

}
