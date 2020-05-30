package io.abhi.hotelmanagement.model.response;

import java.util.List;

import io.abhi.hotelmanagement.entity.HotelTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {

	private long hotelId;
	
	private String hotelName;
	
	private String hotelUrl;
	
	private String type;
	
	private long outletNumber;
	
	private String address;
	
	private String description;
	
	private boolean isHotelActive;
	
	private String imageUrl;
}
