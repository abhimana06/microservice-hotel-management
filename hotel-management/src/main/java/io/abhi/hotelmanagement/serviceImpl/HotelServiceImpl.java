package io.abhi.hotelmanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.abhi.hotelmanagement.common.WebAppConstants;
import io.abhi.hotelmanagement.entity.HotelEntity;
import io.abhi.hotelmanagement.entity.HotelTypeEntity;
import io.abhi.hotelmanagement.exception.BadRequestException;
import io.abhi.hotelmanagement.exception.InValidHotelIdException;
import io.abhi.hotelmanagement.feign.HotelInfoFeign;
import io.abhi.hotelmanagement.feign.RatingFeign;
import io.abhi.hotelmanagement.model.request.HotelRequest;
import io.abhi.hotelmanagement.model.response.HotelInfoResponse;
import io.abhi.hotelmanagement.model.response.HotelResponse;
import io.abhi.hotelmanagement.model.response.RatingsResponse;
import io.abhi.hotelmanagement.repo.HotelRepo;
import io.abhi.hotelmanagement.repo.HotelTypeRepo;
import io.abhi.hotelmanagement.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelTypeRepo hotelTypeRepo;
	
	@Autowired
	private HotelRepo hotelRepo;
	
	@Autowired
	private RestTemplate resttemplate ;
	
	
	@Override
	public HotelResponse getHotel(Long id) {
		HotelEntity hotelEntity = new HotelEntity();
		if(id!=null && id>0 ) 
			 hotelEntity= hotelRepo.findById(id).orElseThrow(() -> new InValidHotelIdException(WebAppConstants.INVALID_ID));
		
		HotelResponse response = new HotelResponse();
		response.setHotelId(hotelEntity.getHotelId());
		response.setHotelName(hotelEntity.getTitle());
		response.setHotelActive(hotelEntity.isActive());
		response.setHotelUrl(hotelEntity.getUrl());
		response.setAddress(hotelEntity.getAddress());
		response.setOutletNumber(hotelEntity.getOutletNumber());
		response.setImageUrl(hotelEntity.getImageUrl());
		HotelTypeEntity typeEntity = hotelTypeRepo.findByTypeCode(hotelEntity.getType().getTypeCode()).orElseThrow(() -> new BadRequestException(WebAppConstants.BAD_REQUEST));
		if(typeEntity!=null)
			response.setType(typeEntity.getHotelType());
		return response; 
	}

	
	@Override
	public void addHotel(HotelRequest request) {
		if(request!=null)
			addOrUpdateHotel(request);
	}
	
	@Override
	public void updateHotel(HotelRequest request) {
		if(request!=null ) {
			HotelEntity hotelEntity= hotelRepo.findById(request.getHotelId()).orElseThrow(() -> new InValidHotelIdException(WebAppConstants.INVALID_ID));
			if(hotelEntity!=null)
				addOrUpdateHotel(request);
		}
	}

	@Override
	public void deleteHotel(Long id) {
		if(id!=null && id>0 ) {
			HotelEntity hotelEntity= hotelRepo.findById(id).orElseThrow(() -> new InValidHotelIdException(WebAppConstants.INVALID_ID));
			if(hotelEntity!=null)
				hotelEntity.setActive(false);
				hotelRepo.save(hotelEntity);
			
		}
	}
	
	private void addOrUpdateHotel(HotelRequest request) {
		HotelEntity hotelEntity = new HotelEntity();
		hotelEntity.setHotelId(request.getHotelId());
		hotelEntity.setTitle(request.getHotelTitle());
		hotelEntity.setUrl(request.getHotelUrl());
		hotelEntity.setOutletNumber(request.getOutletNumber());
		hotelEntity.setImageUrl(request.getImageUrl());
		hotelEntity.setActive(true);
		
		HotelTypeEntity typeEntity = hotelTypeRepo.findByTypeCode(request.getTypeCode())
				.orElseThrow(() -> new BadRequestException(WebAppConstants.BAD_REQUEST));
		if(typeEntity!=null)
			hotelEntity.setType(typeEntity);
		hotelEntity.setAddress(request.getAddress());
		hotelRepo.save(hotelEntity);
		
	}
	

}
