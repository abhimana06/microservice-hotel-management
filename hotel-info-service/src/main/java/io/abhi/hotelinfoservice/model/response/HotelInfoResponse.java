package io.abhi.hotelinfoservice.model.response;

import java.sql.Date;

import lombok.Data;

@Data
public class HotelInfoResponse {
	
	
	private long hotelId;
	
	private String description;
	
	private long floors;
	
	private long rooms;
	
	private String timings;
	
	private String speciality;
	
	private String hotelUrl;
	
	private String price;
	
	private String address;
	
	private String imgUrl;
}
