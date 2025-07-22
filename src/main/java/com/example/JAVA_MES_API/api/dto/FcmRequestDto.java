package com.example.JAVA_MES_API.api.dto;

public class FcmRequestDto {

	private String userId;
	private String fcmToken;
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getFcmToken() {
		return this.fcmToken;
	}
	
	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
}
