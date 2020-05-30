package io.abhi.ratingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.abhi.ratingService.model.request.RatingsRequest;
import io.abhi.ratingService.model.response.GeneralResponse;
import io.abhi.ratingService.model.response.RatingsResponse;
import io.abhi.ratingService.service.RatingsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ratings")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3000, allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.OPTIONS,RequestMethod.PUT})
public class RatingController {
	
	@Autowired
	private RatingsService ratingService;

	

	@GetMapping("/getHotelRating")
	@ApiOperation(value = "To get hotel rating", notes = "Just pass valid hotel Id")
	public ResponseEntity<?> getRating(@RequestParam("id") Long id) {
	    RatingsResponse response = ratingService.getRating(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/addHotelRating")
	@ApiOperation(value = "To add hotel info", notes = "Just pass valid hotel Id and HotelInfoRequest")
	public ResponseEntity<?> addHotelInfo(@RequestParam("hotelId") Long id, @RequestBody RatingsRequest request) {
		ratingService.addRating(id, request);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	
	@PutMapping("/updateHotelRating")
	@ApiOperation(value = "To update hotel info", notes = "Just pass valid hotel Id and HotelInfoRequest")
	public ResponseEntity<?> updateHotelInfo(@RequestParam("hotelId") Long id, @RequestBody RatingsRequest request) {
		ratingService.updateRating(id, request);
		return ResponseEntity.ok(new GeneralResponse());
	}
}
