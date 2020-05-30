package io.abhi.hotelmanagement.serviceImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.abhi.hotelmanagement.common.WebAppConstants;
import io.abhi.hotelmanagement.entity.RoleEntity;
import io.abhi.hotelmanagement.entity.UserEntity;
import io.abhi.hotelmanagement.exception.InValidHotelIdException;
import io.abhi.hotelmanagement.model.request.UserRequest;
import io.abhi.hotelmanagement.repo.RolesRepo;
import io.abhi.hotelmanagement.repo.UserRepo;
import io.abhi.hotelmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RolesRepo rolesRepo;

	
	@Override
	public void addUser(UserRequest request) throws Exception{
		if(request!=null)
			addOrUpdateUser(request);
	}
	
	
	@Override
	public void updateUser(UserRequest request) throws Exception {
		if(request!=null ) {
			UserEntity userEntity= userRepo.findById(request.getUserId()).orElseThrow(() -> new InValidHotelIdException(WebAppConstants.INVALID_ID));
			if(userEntity!=null)
				addOrUpdateUser(request);
		}
	}
	
	
	@Override
	public void deleteUser(Long id) {
		if(id!=null && id>0 ) {
			UserEntity userEntity= userRepo.findById(id).orElseThrow(() -> new InValidHotelIdException(WebAppConstants.INVALID_ID));
			if(userEntity!=null)
				userEntity.setActive(false);
				userRepo.save(userEntity);
			
		}
	}
	
	private void addOrUpdateUser(UserRequest request) throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(request.getUsername());
		userEntity.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
		userEntity.setActive(true);
		List<RoleEntity> roles =  request.getRoles().stream().map(role ->  rolesRepo.findByRolename(role)).collect(Collectors.toList());
		if(roles.isEmpty() || roles.size()<0 || roles==null) {
			throw new RoleNotFoundException("Specific Roles not given in the Request");
		}
		userEntity.setRoles(roles);	
		userRepo.save(userEntity);
	}

	


}
