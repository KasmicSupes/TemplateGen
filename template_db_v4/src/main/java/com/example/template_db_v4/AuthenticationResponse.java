package com.example.template_db_v4;

public class AuthenticationResponse {

	private final String jwt;
	
	

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}



	public String getJwt() {
		return jwt;
	}
	
}
