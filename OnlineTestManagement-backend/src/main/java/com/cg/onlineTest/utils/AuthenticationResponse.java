package com.cg.onlineTest.utils;

public class AuthenticationResponse {

	String jwt;

	public AuthenticationResponse() {
		super();
	}
	
	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
	
}
