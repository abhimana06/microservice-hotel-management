package io.abhi.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.abhi.hotelmanagement.model.request.HotelRequest;
import io.abhi.hotelmanagement.model.request.UserRequest;
import io.abhi.hotelmanagement.model.response.GeneralResponse;
import io.abhi.hotelmanagement.model.response.HotelResponse;
import io.abhi.hotelmanagement.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3000, allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.OPTIONS,RequestMethod.PUT})
@Secured({"ROLE_ADMIN"})
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	@ApiOperation(value = "To add User", notes = "pass UserRequest")
	public ResponseEntity<?> addHotel(@RequestHeader("Authorization") String authHeader, @RequestBody UserRequest request) throws Exception{
		userService.addUser(request);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	
	@PutMapping("/updateHotel")
	@ApiOperation(value = "To update hotel", notes = "pass UserRequest with Valid UserId")
	public ResponseEntity<?> updateHotel(@RequestHeader("Authorization") String authHeader, @RequestBody UserRequest request) throws Exception{
		userService.updateUser(request);
		return ResponseEntity.ok(new GeneralResponse());
	}

	@DeleteMapping("/deleteHotel")
	@ApiOperation(value = "To delete hotel", notes = "pass valid User Id")
	public ResponseEntity<?> deleteHotel(@RequestHeader("Authorization") String authHeader, @RequestParam("userId") Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(new GeneralResponse());
	}
	
	
}
