package io.abhi.hotelmanagement.service;

import org.springframework.stereotype.Service;

import io.abhi.hotelmanagement.model.request.HotelInfoRequest;
import io.abhi.hotelmanagement.model.request.HotelRequest;
import io.abhi.hotelmanagement.model.response.HotelInfoResponse;
import io.abhi.hotelmanagement.model.response.HotelResponse;
import io.abhi.hotelmanagement.model.response.RatingsResponse;

@Service 
public interface HotelService {
	
	public HotelResponse getHotel(Long id);

	public void addHotel(HotelRequest request) ;

	public void updateHotel(HotelRequest request);

	public void deleteHotel(Long id);

}
