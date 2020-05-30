package io.abhi.hotelmanagement.service;

import org.springframework.stereotype.Service;

import io.abhi.hotelmanagement.model.request.HotelInfoRequest;
import io.abhi.hotelmanagement.model.request.RatingsRequest;
import io.abhi.hotelmanagement.model.response.HotelInfoResponse;
import io.abhi.hotelmanagement.model.response.RatingsResponse;

@Service
public interface InfoRatingService {
	
	public HotelInfoResponse getHotelInfo(Long id);

	public RatingsResponse getHotelRating(Long id);

	public void addHotelInfo(Long id, HotelInfoRequest request);

	public void updateHotelInfo(Long id, HotelInfoRequest request);

	public void addRating(Long id, RatingsRequest request);

	public void updateRating(Long id, RatingsRequest request);

}
