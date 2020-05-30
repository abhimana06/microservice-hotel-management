package io.abhi.hotelmanagement.model.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	@NotNull
	private long userId;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotEmpty
	private Set<String> roles;
	

}
