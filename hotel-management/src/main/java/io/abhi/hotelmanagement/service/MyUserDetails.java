package io.abhi.hotelmanagement.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.abhi.hotelmanagement.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3418330142631388596L;
	private Long userId;
	private String username;
	private String password;
	private boolean isActive;
	private List<GrantedAuthority> authorities;
	
	

	public static MyUserDetails createUser(UserEntity user) {
		List<GrantedAuthority> authorities =  user.getRoles().stream().map(role ->
        new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());		
		return new MyUserDetails(user.getUserId(),user.getUsername(),user.getPassword(), user.isActive(),authorities) ;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
