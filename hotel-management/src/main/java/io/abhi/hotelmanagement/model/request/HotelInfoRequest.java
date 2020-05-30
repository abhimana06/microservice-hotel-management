package io.abhi.hotelmanagement.model.request;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class HotelInfoRequest {
	
	@NotBlank
	private String description;
	
	@NotNull
	private long floors;
	
	@NotNull
	private long rooms;
	
	@NotBlank
	private String timings;
	
	private String speciality;

	private String hotelUrl;
	
	@NotBlank
	private String price;
	
	@NotBlank
	private String address;
	
	private String imgUrl;

}
