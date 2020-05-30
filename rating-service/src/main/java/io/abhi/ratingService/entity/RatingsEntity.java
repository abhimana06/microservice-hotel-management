package io.abhi.ratingService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HOTEL_RATING")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingsEntity {

	@Id
	@Column(name = "HOTELID")
	@NotNull
	private long hotelId;
	
	@Column(name = "FOOD_RATING")
	@NotNull
	private long foodRating;
	
	@Column(name = "F_FEEDBACK")
	@NotBlank
	private String foodFeedback;
	
	
	@Column(name = "SERVICE_RATING")
	@NotNull
	private long serviceRating;
	
	@Column(name = "S_FEEDBACK")
	@NotBlank
	private String serviceFeedback;
	
	
	@Column(name = "AMBIENCE_RATING")
	@NotNull
	private long ambienceRating;
	
	@Column(name = "A_FEEDBACK")
	@NotBlank
	private String ambienceFeedback;
	
	
	
	
}
