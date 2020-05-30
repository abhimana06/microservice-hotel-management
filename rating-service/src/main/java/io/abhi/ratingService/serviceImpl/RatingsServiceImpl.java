package io.abhi.ratingService.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.abhi.ratingService.common.WebAppConstants;
import io.abhi.ratingService.entity.HotelEntity;
import io.abhi.ratingService.entity.HotelInfoEntity;
import io.abhi.ratingService.entity.RatingsEntity;
import io.abhi.ratingService.exception.InValidHotelIdException;
import io.abhi.ratingService.model.request.RatingsRequest;
import io.abhi.ratingService.model.response.RatingsResponse;
import io.abhi.ratingService.repo.HotelRepo;
import io.abhi.ratingService.repo.RatingsRepo;
import io.abhi.ratingService.service.RatingsService;

@Service
public class RatingsServiceImpl implements RatingsService{
	
	@Autowired
	private HotelRepo hotelRepo;
	
	@Autowired
	private RatingsRepo ratingsRepo;
	
	

	@Override
	public RatingsResponse getRating(Long id) {
		RatingsEntity ratingEntity = ratingsRepo.findById(id).orElseThrow(()-> new InValidHotelIdException(WebAppConstants.INVALID_ID));
		RatingsResponse response = new RatingsResponse();
		response.setHotelId(id);
		response.setFoodRating(ratingEntity.getFoodRating());
		response.setFoodFeedback(ratingEntity.getFoodFeedback());
		response.setAmbienceRating(ratingEntity.getAmbienceRating());
		response.setAmbienceFeedback(ratingEntity.getAmbienceFeedback());
		response.setServiceRating(ratingEntity.getServiceRating());
		response.setServiceFeedback(ratingEntity.getServiceFeedback());
		return response;
	}

	@Override
	@Transactional
	public void addRating(Long id, RatingsRequest request) {
		addOrUpdateRating(id,request );
	}
	
	@Override
	@Transactional
	public void updateRating(Long id, RatingsRequest request) {
		addOrUpdateRating(id,request );
	}

	private void addOrUpdateRating(Long id, RatingsRequest request) {
		HotelEntity hotelEntity = hotelRepo.findById(id).orElseThrow(()-> new InValidHotelIdException(WebAppConstants.INVALID_ID));
		
		RatingsEntity ratingEntity = new RatingsEntity();
		ratingEntity.setHotelId(hotelEntity.getHotelId());
		ratingEntity.setFoodRating(request.getFoodRating());
		ratingEntity.setFoodFeedback(request.getFoodFeedback());
		ratingEntity.setAmbienceRating(request.getAmbienceRating());
		ratingEntity.setAmbienceFeedback(request.getAmbienceFeedback());
		ratingEntity.setServiceRating(request.getServiceRating());
		ratingEntity.setServiceFeedback(request.getServiceFeedback());
		ratingsRepo.save(ratingEntity);
		
	}

}
