package io.abhi.hotelmanagement.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.abhi.hotelmanagement.common.WebAppConstants;
import io.abhi.hotelmanagement.enumeration.TokenStatus;
import io.abhi.hotelmanagement.exception.InvalidTokenException;
import io.abhi.hotelmanagement.repo.TokenRepo;
import io.abhi.hotelmanagement.service.AuthTokenService;
import io.abhi.hotelmanagement.service.MyUserDetailService;
import io.abhi.hotelmanagement.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Component	
@Slf4j
public class BaseJwtAuthentication  { 

	 
	@Autowired
	private AuthTokenService tokenService;

	@Autowired
	private TokenRepo tokenRepo;

	@Autowired
	private MyUserDetailService userDetailsService;
	
	private static final List<String> EXCLUDE_URL = Arrays.asList("/",
			"/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/**/*.ttf",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/webjars/**", "/swagger-ui.html/**");

	
	//public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)throws ServletException, IOException {

	public void doAuthorization(HttpServletRequest request)throws ServletException, IOException {
		

			log.info("entering BaseJwtAuthentication");
			String jwt = request.getHeader("Authorization");
			if (StringUtils.hasText(jwt) && tokenService.validateTokenByStatus(jwt, TokenStatus.ACTIVE)) {
				Long userId = JwtUtil.getUserIdFromJWTinLong(jwt);

				UserDetails userDetails = userDetailsService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);

			}else {
				throw new InvalidTokenException(WebAppConstants.INVALID_AUTH_TOKEN);
			}
		

		
	}

	
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
	}


}
	

	

	 