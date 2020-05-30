package io.abhi.hotelmanagement.serviceImpl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.abhi.hotelmanagement.common.WebAppConstants;
import io.abhi.hotelmanagement.entity.TokenEntity;
import io.abhi.hotelmanagement.entity.UserEntity;
import io.abhi.hotelmanagement.enumeration.TokenStatus;
import io.abhi.hotelmanagement.exception.InvalidIdException;
import io.abhi.hotelmanagement.exception.InvalidTokenException;
import io.abhi.hotelmanagement.repo.TokenRepo;
import io.abhi.hotelmanagement.repo.UserRepo;
import io.abhi.hotelmanagement.service.AuthTokenService;
import io.abhi.hotelmanagement.service.MyUserDetails;
import io.abhi.hotelmanagement.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AuthTokenServiceImpl implements AuthTokenService{
	
	@Autowired
	private TokenRepo tokenRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public String createToken(MyUserDetails userInfo) {
		Date expiryDate = new Date(System.currentTimeMillis()+6000*10*5);
		final String jwt = JwtUtil.generateToken(userInfo, expiryDate);
		UserEntity user = userRepo.findById(userInfo.getUserId()).orElseThrow(()-> new InvalidIdException(WebAppConstants.INVALID_ID));
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUserId(user);
		tokenEntity.setUsername(user.getUsername());
		tokenEntity.setAccessToken(jwt);
		tokenEntity.setTokenStatus(TokenStatus.ACTIVE);
		tokenEntity.setTokenExpiry(expiryDate);
		
		tokenRepo.save(tokenEntity);
		
		return jwt;
	}
	

	@Override
	public boolean validateTokenByStatus(String jwtToken, TokenStatus active) {
		boolean isValid = validateOrThrow(jwtToken);
		if (isValid) {
			TokenEntity entity = tokenRepo.checkToken(jwtToken, active);
			if (entity != null) {
				log.info("entity found");
				return true;
			}
		}

		return false;
	}


	private boolean validateOrThrow(String token) {
		try {
			JwtUtil.validateToken(token);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
			throw new SignatureException(WebAppConstants.INVALID_AUTH_TOKEN);
			//ex.printStackTrace();
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT tokennnn");
			tokenRepo.changeTokenStatus(JwtUtil.getPlainToken(token), false, TokenStatus.EXPIRED);
			throw new InvalidTokenException(WebAppConstants.TOKEN_INACTIVE);
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}


}
