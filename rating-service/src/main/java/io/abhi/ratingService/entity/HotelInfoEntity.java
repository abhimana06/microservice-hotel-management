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
@Table(name = "HOTEL_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelInfoEntity {
	
	@Id
	@Column(name = "HOTELID")
	@NotNull
	private long hotelId;
	@Column(name = "DESCRIPTION")
	
	@NotBlank
	private String description;
	
	@Column(name = "FLOORS")
	@NotNull
	private long floors;
	
	@Column(name = "ROOMS")
	@NotNull
	private long rooms;
	
	@Column(name = "TIMINGS")
	@NotBlank
	private String timings;
	
	@Column(name = "SPECIALITY")
	@NotBlank
	private String speciality;
}
