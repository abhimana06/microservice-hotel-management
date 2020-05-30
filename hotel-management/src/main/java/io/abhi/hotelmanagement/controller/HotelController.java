package io.abhi.hotelmanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.abhi.hotelmanagement.annotation.AnalyzeAuth;
import io.abhi.hotelmanagement.annotation.Authorize;
import io.abhi.hotelmanagement.common.WebAppConstants;
import io.abhi.hotelmanagement.jwt.BaseJwtAuthentication;
import io.abhi.hotelmanagement.model.request.HotelRequest;
import io.abhi.hotelmanagement.model.response.GeneralResponse;
import io.abhi.hotelmanagement.model.response.HotelInfoResponse;
import io.abhi.hotelmanagement.model.response.HotelResponse;
import io.abhi.hotelmanagement.service.HotelService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hotel")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3000, allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.OPTIONS,RequestMethod.PUT})
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@EnableCircuitBreaker
public class HotelController {
	
	
	@Context
	private HttpServletRequest httpRequest;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private BaseJwtAuthentication baseJwtAuth;
	
	
	
	
	@GetMapping("/getWelcome")
	@ApiOperation(value = "To get hotel", notes = "Just pass valid hotel Id")
	public ResponseEntity<?> getWelcome(@RequestParam("hotelId") String id) {
		String welcome = "";
		if(id!="abhi")
			 welcome = "Welcome to Hotel Management";
		return ResponseEntity.ok(welcome);
	}
	
	
	
	@GetMapping("/getHotel")
	@ApiOperation(value = "To get hotel", notes = "Just pass valid hotel Id")
	public ResponseEntity<?> getHotel(@RequestParam("hotelId") Long id)  {
	    HotelResponse response = hotelService.getHotel(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/addHotel")
	@ApiOperation(value = "To add hotel", notes = "pass HotelRequest")
	@AnalyzeAuth(enabled = WebAppConstants.isSecurityEnabled)
	public ResponseEntity<?> addHotel(@RequestHeader("Authorization") String authHeader, @RequestBody HotelRequest request) throws ServletException, IOException {
		baseJwtAuth.doAuthorization(httpRequest);
	    hotelService.addHotel(request);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	@PutMapping("/updateHotel")
	@ApiOperation(value = "To update hotel", notes = "pass HotelRequest with Valid HotelId")
	@AnalyzeAuth(enabled = WebAppConstants.isSecurityEnabled)
	public ResponseEntity<?> updateHotel(@RequestHeader("Authorization") String authHeader, @RequestBody HotelRequest request) throws ServletException, IOException {
		baseJwtAuth.doAuthorization(httpRequest);
	    hotelService.updateHotel(request);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	
	@DeleteMapping("/deleteHotel")
	@ApiOperation(value = "To delete hotel", notes = "pass valid hotel Id")
	@AnalyzeAuth(enabled = WebAppConstants.isSecurityEnabled)
	public ResponseEntity<?> deleteHotel(@RequestHeader("Authorization") String authHeader, @RequestParam("hotelId") Long id) throws ServletException, IOException {
		baseJwtAuth.doAuthorization(httpRequest);
	    hotelService.deleteHotel(id);
		return ResponseEntity.ok(new GeneralResponse());
	}
	

}
