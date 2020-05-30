
package io.abhi.hotelmanagement.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;



import io.abhi.hotelmanagement.annotation.AnalyzeAuth;
import io.abhi.hotelmanagement.common.WebAppConstants;
import io.abhi.hotelmanagement.entity.TokenEntity;
import io.abhi.hotelmanagement.enumeration.TokenStatus;
import io.abhi.hotelmanagement.exception.InvalidTokenException;
import io.abhi.hotelmanagement.repo.TokenRepo;
import io.abhi.hotelmanagement.service.AuthTokenService;
import io.abhi.hotelmanagement.service.MyUserDetailService;
import io.abhi.hotelmanagement.util.JwtUtil;
import io.abhi.hotelmanagement.util.UserSessionUtils;
import lombok.extern.slf4j.Slf4j;

@Component

@Aspect

@Slf4j
public class AuthAAOPAspect {

	@Autowired
	private AuthTokenService tokenService;

	@Autowired
	private TokenRepo tokenRepo;

	@Autowired
	private MyUserDetailService userDetailsService;

	@Around(" execution(* io.abhi.hotelmanagement.controller..*.*(..)) && @annotation(analyzeAuthRequest) ")
	public Object analyze(ProceedingJoinPoint joinPoint, AnalyzeAuth analyzeAuthRequest) throws Throwable {

		
		log.info("analyze====AuthAAOPAspect==");
		log.info(">>>>>>>>>"+joinPoint.getArgs().toString());
		Object[] objects = joinPoint.getArgs();
		
		for (Object object : objects) {
			log.info("object>>>"+object);
		}
		
		String eventType = analyzeAuthRequest.eventType();
		HttpServletResponse servletResponse = UserSessionUtils.getHttpResponse(objects);
		
		HttpServletRequest servletRequest = UserSessionUtils.getHttpRequest(objects);;
		if (!analyzeAuthRequest.enabled()) {
			return joinPoint.proceed();
		}
	
		String jwt = objects[0].toString();
		
		if (jwt == null || !jwt.contains("Bearer")) {
			log.info("InvalidTokenException");
			throw new InvalidTokenException(WebAppConstants.INVALID_AUTH_TOKEN);
		}
		String token = jwt.substring("Bearer".length()).trim();
		
		TokenEntity jwtToken = tokenRepo.findByAccessToken(token);
		
		if(!token.equalsIgnoreCase(jwtToken.getAccessToken())) {
			throw new InvalidTokenException(WebAppConstants.INVALID_AUTH_TOKEN);
		}
		log.info("done till here"
				+ "jwtToken:" 
				+ jwtToken.getAccessToken());
			
			if (StringUtils.hasText(jwtToken.getAccessToken()) && tokenService.validateTokenByStatus(jwtToken.getAccessToken(), TokenStatus.ACTIVE)) {
				log.info("entered securitycontext block");
				String username = JwtUtil.getUserIdFromJWT(jwtToken.getAccessToken());
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(servletRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			else {
				log.info("not able to set SecurityContextHolder");
			}
			Object ret = joinPoint.proceed();
			return ret;
	
	}

	
  }
