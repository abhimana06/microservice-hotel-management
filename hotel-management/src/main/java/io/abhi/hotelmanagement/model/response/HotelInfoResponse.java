package io.abhi.hotelmanagement.model.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelInfoResponse {
	
	
	private long hotelId;
	
	private String description;
	
	private long floors;
	
	private long rooms;
	
	private String timings;
	
	private String speciality;
}
