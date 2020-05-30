package io.abhi.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.abhi.hotelmanagement.model.request.AuthenticationRequest;
import io.abhi.hotelmanagement.model.response.AuthenticationResponse;
import io.abhi.hotelmanagement.service.AuthTokenService;
import io.abhi.hotelmanagement.service.AuthorizationService;
import io.abhi.hotelmanagement.service.MyUserDetails;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/authorization")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3000, allowCredentials = "true",methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.OPTIONS,RequestMethod.PUT})
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class AuthorizationController {
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	
	@PostMapping("/authenticate")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
		AuthenticationResponse response = new AuthenticationResponse();
		log.info("call");
		try {
			 Authentication authentication = authorizationService.getAuthorization(request.getUsername(),request.getPassword());		 
					 
			 SecurityContextHolder.getContext().setAuthentication(authentication);
			 
			 MyUserDetails userInfo = (MyUserDetails)authentication.getPrincipal();
			 
			 String jwt = authTokenService.createToken(userInfo);
			 log.info("output jwt::"+jwt);
			 response.setJwtToken(jwt);
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid UserName and Password", e);
		}
		return response;
	}
	

}
