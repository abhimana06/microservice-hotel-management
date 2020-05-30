package io.abhi.hotelmanagement.model.response;

public class AuthenticationResponse {

	private String jwtToken;

	public AuthenticationResponse() {

	}

	public AuthenticationResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
