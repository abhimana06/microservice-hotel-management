package io.abhi.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.abhi.hotelmanagement.model.request.HotelInfoRequest;
import io.abhi.hotelmanagement.model.request.RatingsRequest;
import io.abhi.hotelmanagement.model.response.GeneralResponse;
import io.abhi.hotelmanagement.model.response.HotelInfoResponse;
import io.abhi.hotelmanagement.model.response.RatingsResponse;
import io.abhi.hotelmanagement.service.HotelService;
import io.abhi.hotelmanagement.service.InfoRatingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hotelInfoRating")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3000, allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.OPTIONS,RequestMethod.PUT})
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class InfoRatingController {
	
	@Autowired
	private HotelService hotelService;
	
	
	@Autowired
	private InfoRatingService infoRateService;

	@GetMapping("/getHotelInfo")
	@ApiOperation(value = "To get hotel information", notes = "Just pass valid hotel Id")
	public ResponseEntity<?> getHotelInfo(@RequestParam("hotelId") Long id) {
	    HotelInfoResponse response = infoRateService.getHotelInfo(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/addHotelInfo")
	@ApiOperation(value = "To add hotel info", notes = "Just pass valid hotel Id and HotelInfoRequest")
	public ResponseEntity<?> addHotelInfo(@RequestParam("hotelId") Long id, @RequestBody HotelInfoRequest request) {
		infoRateService.addHotelInfo(id, request);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	
	@PutMapping("/updateHotelInfo")
	@ApiOperation(value = "To update hotel info", notes = "Just pass valid hotel Id and HotelInfoRequest")
	public ResponseEntity<?> updateHotelInfo(@RequestParam("hotelId") Long id, @RequestBody HotelInfoRequest request) {
		infoRateService.updateHotelInfo(id, request);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	@GetMapping("/getHotelRating")
	@ApiOperation(value = "To get hotel information", notes = "Just pass valid hotel Id")
	public ResponseEntity<?> getHotelRating(@RequestParam("id") Long id) {
	    RatingsResponse response = infoRateService.getHotelRating(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/addHotelRating")
	@ApiOperation(value = "To add hotel info", notes = "Just pass valid hotel Id and HotelInfoRequest")
	public ResponseEntity<?> addHotelInfo(@RequestParam("hotelId") Long id, @RequestBody RatingsRequest request) {
		infoRateService.addRating(id, request);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	
	@PutMapping("/updateHotelRating")
	@ApiOperation(value = "To update hotel info", notes = "Just pass valid hotel Id and HotelInfoRequest")
	public ResponseEntity<?> updateHotelInfo(@RequestParam("hotelId") Long id, @RequestBody RatingsRequest request) {
		infoRateService.updateRating(id, request);
		return ResponseEntity.ok(new GeneralResponse());
	}
}
