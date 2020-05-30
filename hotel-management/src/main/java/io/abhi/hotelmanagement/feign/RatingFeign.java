package io.abhi.hotelmanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.abhi.hotelmanagement.model.request.RatingsRequest;
import io.abhi.hotelmanagement.model.response.RatingsResponse;

@FeignClient(url = "http://rating-service:8082/ratings", value = "RATING")
public interface RatingFeign {

	@GetMapping("/getHotelRating" )
	public RatingsResponse getHotelRating(@RequestParam("id") Long id);
	
	@PostMapping("/addHotelRating?hotelId={id}")
	public void addRating(@RequestParam("id") Long id, @RequestBody RatingsRequest request);
	
	@PutMapping("/updateHotelRating?hotelId={id}")
	public void updateRating(@RequestParam("id") Long id, @RequestBody RatingsRequest request);

}
