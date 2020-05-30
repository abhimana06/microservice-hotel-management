package io.abhi.hotelinfoservice.service;

import org.springframework.stereotype.Service;

import io.abhi.hotelinfoservice.entity.HotelInfoEntity;
import io.abhi.hotelinfoservice.model.request.HotelInfoRequest;

@Service
public interface HotelInfoService {

	public HotelInfoEntity getHotel(Long id) throws Exception;

	public void addHotelInfo(Long id, HotelInfoRequest request);

	public void updateHotelInfo(Long id, HotelInfoRequest request);

}
