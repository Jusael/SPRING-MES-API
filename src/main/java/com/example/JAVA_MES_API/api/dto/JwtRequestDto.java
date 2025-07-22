package com.example.JAVA_MES_API.api.dto;

public class JwtRequestDto {

	private String userId;
	private String jwtToken;
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getFcmToken() {
		return this.jwtToken;
	}
	
	public void setFcmToken(String fcmToken) {
		this.jwtToken = fcmToken;
	}
}
