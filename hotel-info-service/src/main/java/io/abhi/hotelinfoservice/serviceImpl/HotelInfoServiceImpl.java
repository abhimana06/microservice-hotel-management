package io.abhi.hotelinfoservice.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.abhi.hotelinfoservice.common.WebAppConstants;
import io.abhi.hotelinfoservice.entity.HotelEntity;
import io.abhi.hotelinfoservice.entity.HotelInfoEntity;
import io.abhi.hotelinfoservice.exception.InValidHotelIdException;
import io.abhi.hotelinfoservice.model.request.HotelInfoRequest;
import io.abhi.hotelinfoservice.model.response.HotelInfoResponse;
import io.abhi.hotelinfoservice.repo.HotelInfoRepo;
import io.abhi.hotelinfoservice.repo.HotelRepo;
import io.abhi.hotelinfoservice.service.HotelInfoService;

@Service
public class HotelInfoServiceImpl implements HotelInfoService{
	
	@Autowired
	private HotelRepo hotelRepo;
	
	@Autowired
	private HotelInfoRepo hotelInfoRepo;
	
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public HotelInfoEntity getHotel(Long hotelId) throws Exception{
      try {
    	  final String URL = "https://gist.githubusercontent.com/abhimana06/68884d2456ff62e92ae469f962d7cd88/raw/8b0615df8df7367523dd8f2c27cc9a9fae562194/hotel";
		  
    	  String jsonString =restTemplate.getForObject(URL+hotelId, String.class);
		  
		  ObjectMapper mapper = new ObjectMapper();
		
		  mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		  HotelInfoResponse inforesponse = mapper.readValue(jsonString, HotelInfoResponse.class);
		 
		  HotelInfoEntity infoEntity = new HotelInfoEntity();
		  infoEntity.setDescription(inforesponse.getDescription());
		  infoEntity.setFloors(inforesponse.getFloors());
		  infoEntity.setHotelId(inforesponse.getHotelId());
		  infoEntity.setRooms(inforesponse.getRooms());
		  infoEntity.setSpeciality(inforesponse.getSpeciality());
		  infoEntity.setHotelUrl(inforesponse.getHotelUrl());
		  infoEntity.setPrice(inforesponse.getPrice());
		  infoEntity.setImgUrl(inforesponse.getImgUrl());
		  infoEntity.setAddress(inforesponse.getAddress());
		  infoEntity.setTimings(inforesponse.getTimings());
		  hotelInfoRepo.save(infoEntity);
		  return infoEntity;
      }
      catch(Exception ex) {
    	 ex.printStackTrace(); 
      }
      return new HotelInfoEntity();
	}

	@Override
	@Transactional
	public void addHotelInfo(Long id, HotelInfoRequest request) {
		addOrUpdateInfo(id,request );
	}
	
	@Override
	@Transactional
	public void updateHotelInfo(Long id, HotelInfoRequest request) {
		addOrUpdateInfo(id,request );
	}

	private void addOrUpdateInfo(Long id, HotelInfoRequest request) {
		HotelEntity hotelEntity = hotelRepo.findById(id).orElseThrow(()-> new InValidHotelIdException(WebAppConstants.INVALID_ID));
		
		HotelInfoEntity infoEntity = new HotelInfoEntity();
		infoEntity.setHotelId(hotelEntity.getHotelId());
		infoEntity.setDescription(request.getDescription());
		infoEntity.setFloors(request.getFloors());
		infoEntity.setRooms(request.getRooms());
		infoEntity.setSpeciality(request.getSpeciality());
		infoEntity.setTimings(request.getTimings());
		infoEntity.setAddress(request.getAddress());
		infoEntity.setHotelUrl(request.getHotelUrl());
		infoEntity.setPrice(request.getPrice());
		infoEntity.setImgUrl(request.getImgUrl());
		hotelInfoRepo.save(infoEntity);
		
	}
	
	
	/*
	 * HotelInfoResponse response =restTemplate.getForObject(
	 * "https://www.hotel.info/hotel/details?language=en&currency=EUR&arrival_date=2020-05-27&departure_date=2020-05-28&number_of_persons=1&number_of_rooms=1&"
	 * + "hotel_id="+id+"&source=Hotel%20List\n" , HotelInfoResponse.class);
	 */

}
