package io.abhi.hotelinfoservice.controller;

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

import io.abhi.hotelinfoservice.entity.HotelInfoEntity;
import io.abhi.hotelinfoservice.model.request.HotelInfoRequest;
import io.abhi.hotelinfoservice.model.response.GeneralResponse;
import io.abhi.hotelinfoservice.model.response.HotelInfoResponse;
import io.abhi.hotelinfoservice.service.HotelInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hotelInfo")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3000, allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.OPTIONS,RequestMethod.PUT})
public class HotelInfoController {
	
	@Autowired
	private HotelInfoService infoService;
	
	@GetMapping("/getHotelInformation")
	@ApiOperation(value = "To get hotel", notes = "Just pass valid hotel Id")
	public ResponseEntity<?> getHotelInfo() {
	    HotelInfoResponse response = new HotelInfoResponse();
	    response.setHotelId(20);
	    response.setDescription("EMPTY");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getHotelInfo")
	@ApiOperation(value = "To get hotel", notes = "Just pass valid hotel Id")
	public ResponseEntity<?> getHotel(@RequestParam("hotelId") Long id) throws Exception{
	    HotelInfoEntity response = infoService.getHotel(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/addHotelInfo")
	@ApiOperation(value = "To add hotel info", notes = "Just pass valid hotel Id and HotelInfoRequest")
	public ResponseEntity<?> addHotelInfo(@RequestParam("hotelId") Long id, @RequestBody HotelInfoRequest request) {
		infoService.addHotelInfo(id, request);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	
	@PutMapping("/updateHotelInfo")
	@ApiOperation(value = "To update hotel info", notes = "Just pass valid hotel Id and HotelInfoRequest")
	public ResponseEntity<?> updateHotelInfo(@RequestParam("hotelId") Long id, @RequestBody HotelInfoRequest request) {
		infoService.updateHotelInfo(id, request);
		return ResponseEntity.ok(new GeneralResponse());
	}
}
