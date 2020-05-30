package io.abhi.hotelmanagement.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.abhi.hotelmanagement.entity.UserEntity;
import io.abhi.hotelmanagement.exception.InvalidIdException;
import io.abhi.hotelmanagement.repo.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MyUserDetails myUserDetails;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 UserEntity user = userRepo.findByUsernameIgnoreCase(username)
	                .orElseThrow(() -> 
	                        new UsernameNotFoundException("User with UserName:" + username + " not found ")
	        );
		 return myUserDetails.createUser(user);
	}

	

	public UserDetails loadUserById(Long userId) {
		 UserEntity user = userRepo.findById(userId)
	                .orElseThrow(() -> 
	                        new InvalidIdException("User with UserName:" + userId + " not found ")
	        );
		 return myUserDetails.createUser(user);
	}

}
