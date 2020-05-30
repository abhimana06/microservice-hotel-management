package io.abhi.hotelmanagement.model.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {
	
	@NotNull
	private long hotelId;
	
	@NotBlank
	private String hotelTitle;
	
	@NotBlank
	private String hotelUrl;
	
	private String typeCode;
	
	@NotNull
	private long outletNumber;
	
	private String address;
	
	private String description;
	
	@NotNull
	private boolean isHotelActive;
	
	private String imageUrl;
	
	
	

}
