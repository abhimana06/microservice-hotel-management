package io.abhi.ratingService.service;

import io.abhi.ratingService.model.request.RatingsRequest;
import io.abhi.ratingService.model.response.RatingsResponse;

public interface RatingsService {

	public RatingsResponse getRating(Long id) ;

	public void addRating(Long id, RatingsRequest request);

	public void updateRating(Long id, RatingsRequest request);
		

}
