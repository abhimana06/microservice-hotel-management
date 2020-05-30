package io.abhi.hotelmanagement.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingsResponse {
	
	private long hotelId;
	
	private long foodRating;
	
	private String foodFeedback;
	
	
	private long serviceRating;
	
	private String serviceFeedback;
	
	private long ambienceRating;
	
	private String ambienceFeedback;
}
