package io.abhi.hotelmanagement.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AuthorizationService {

	Authentication getAuthorization(String username, String password);
	
	

}
