package io.abhi.hotelmanagement.model.request;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class RatingsRequest {
	
	@NotNull
	private long foodRating;
	
	@NotBlank
	private String foodFeedback;
	
	
	@NotNull
	private long serviceRating;
	
	@NotBlank
	private String serviceFeedback;
	
	@NotNull
	private long ambienceRating;
	
	@NotBlank
	private String ambienceFeedback;

}
