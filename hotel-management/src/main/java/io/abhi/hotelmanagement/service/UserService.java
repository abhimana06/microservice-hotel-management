package io.abhi.hotelmanagement.service;

import org.springframework.stereotype.Service;

import io.abhi.hotelmanagement.model.request.UserRequest;

@Service
public interface UserService {

	public void addUser(UserRequest request) throws Exception;

	public void updateUser(UserRequest request) throws Exception;

	public void deleteUser(Long id);	
	
}
