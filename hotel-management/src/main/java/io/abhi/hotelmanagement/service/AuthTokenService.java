package io.abhi.hotelmanagement.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.abhi.hotelmanagement.enumeration.TokenStatus;

@Service
public interface AuthTokenService {

	public String createToken(MyUserDetails userInfo) ;
	
	public boolean validateTokenByStatus(String jwt, TokenStatus active);
	

}
